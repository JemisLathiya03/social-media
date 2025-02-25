package com.socialmedia.service.implementation;

import com.socialmedia.Exception.UnauthorizedUserException;
import com.socialmedia.dto.*;
import com.socialmedia.enumeration.VerificationType;
import com.socialmedia.mapper.UsersMapper;
import com.socialmedia.modal.EmailVerification;
import com.socialmedia.modal.Role;
import com.socialmedia.modal.Themes;
import com.socialmedia.modal.Users;
import com.socialmedia.repository.EmailVerificationRepo;
import com.socialmedia.repository.RoleRepo;
import com.socialmedia.repository.ThemesRepo;
import com.socialmedia.repository.UsersRepo;
import com.socialmedia.service.abstraction.AuthUserDetailService;
import com.socialmedia.service.abstraction.EmailService;
import com.socialmedia.service.abstraction.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthUserDetailServiceImpl implements AuthUserDetailService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ThemesRepo themesRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private MailService mailService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private EmailVerificationRepo emailVerificationRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private void sendEmailToVerifyCode(UsersDto usersDto,Boolean isRegister) throws MessagingException {

        EmailDetails emailDetails = new EmailDetails();

        emailVerificationRepo.updateIsExpiredByEmail(usersDto.getEmail(),true);

        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);

        LocalDateTime newDateTime = LocalDateTime.now().plusMinutes(10);

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setRecipientEmail(usersDto.getEmail());
        emailVerification.setVerificationType(isRegister ? VerificationType.REGISTER : VerificationType.CHANGEPASS);
        emailVerification.setVerificationCode(randomNumber);
        emailVerification.setExpirationTime(newDateTime);



        emailDetails.setRecipient(usersDto.getEmail());
            emailDetails.setSubject("The Verification Code");

            String htmlBody = "<html><body>" + "<h2>Email Verification</h2>" + "<p>Hello,</p>" + "<p>Thank you for registering with us! To complete your registration, please enter the following verification code:</p>" + "<h3 style='font-size: 24px; color: #2196F3; font-weight: bold; text-align: center;'>" + emailVerification.getVerificationCode() + "</h3>" + "<p>This code is valid for the next 15 minutes. Please enter it in the required field on the website to verify your account.</p>" + "<p>If you did not request this, please ignore this email.</p>" + "<p>Thank you,</p>" + "<p>The [Your Company Name] Team</p>" + "</body></html>";

            emailDetails.setMsgBody(htmlBody);

//            emailService.sendSimpleMail(emailDetails);

            // Create an object with the combined message

        emailVerificationRepo.save(emailVerification);

    }

    @Override
    public ResponseDto<Object> registerUserService(UsersDto usersDto) {
//        ResponseTokenDto responseTokenDto = new ResponseTokenDto();

        List<Users> users = usersRepo.findUserByEmail(usersDto.getEmail());
        if (users.isEmpty()) {

            if (usersDto.getPassword().equals(usersDto.getConfirmPassword())) {

                Users user = usersMapper.usersDtoToUser(usersDto);
                user.setPassword(encoder.encode(usersDto.getPassword()));

                List<Role> roles = roleRepo.findByDefaultRole(true);

                if (!roles.isEmpty()) {
                    user.setRole(roles.get(0));
                }

                List<Themes> themes = themesRepo.findByModificationFalseAndCreatedUserEquals(0L);
                List<Long> availableThemes = new ArrayList<>();

                Themes darkTheme = null;

                if (!themes.isEmpty()) {
                    darkTheme = themes.stream().filter(themes1 -> themes1.getThemeName().equals("dark")).collect(Collectors.toList()).get(0);
                    availableThemes = themes.stream().map(Themes::getThemeId).collect(Collectors.toList());

                    user.setCurrentTheme(darkTheme);
                    user.setAvailableThemes(availableThemes);

                }

                try {

                    sendEmailToVerifyCode(usersDto, true);
                    // Create an object with the combined message
                    Map<String, String> message = new HashMap<>();
                    message.put("message", "You got verification code on your email.");

                    usersRepo.save(user);

                    // Create a response object
                    ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.OK.value(), "success", List.of(message), true);


                    return responseDto;
                } catch (MessagingException e) {

                    // Create an object with the combined message
                    Map<String, String> message = new HashMap<>();
                    message.put("message", "You don't get email please try again later.");

                    ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "failure", List.of(message), false);
                    return responseDto;
                }

            } else {
                throw new UnauthorizedUserException("Unauthorized", "Your password is mismatched.");
            }

        } else {

            Users user = users.get(0);

            if (!user.getIsRegister()){
                if (usersDto.getPassword().equals(usersDto.getConfirmPassword())) {

                    try {

                        sendEmailToVerifyCode(usersDto, true);
                        // Create an object with the combined message
                        Map<String, String> message = new HashMap<>();
                        message.put("message", "You got verification code on your email.");

                        // Create a response object
                        ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.OK.value(), "success", List.of(message), true);


                        return responseDto;
                    } catch (MessagingException e) {

                        // Create an object with the combined message
                        Map<String, String> message = new HashMap<>();
                        message.put("message", "You don't get email please try again later.");

                        ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "failure", List.of(message), false);
                        return responseDto;
                    }

                } else {
                    throw new UnauthorizedUserException("Unauthorized", "Your password is mismatched.");
                }
            }else {
                throw new UnauthorizedUserException("Unauthorized", "User with this email address already exist.");
            }

        }


    }

    @Override
    public ResponseDto<Object> verificationCodeService(VerificationCodeDto verificationCodeDto){

        List<EmailVerification> emailVerifications = emailVerificationRepo.findByRecipientEmailAndIsExpired(verificationCodeDto.getEmail(), false);

        if (!emailVerifications.isEmpty()){
            EmailVerification emailVerification = emailVerifications.get(0);
            if (LocalDateTime.now().isBefore(emailVerification.getExpirationTime())){
                if (verificationCodeDto.getVerificationCode().equals(emailVerification.getVerificationCode())){
                    emailVerification.setIsExpired(true);

                    emailVerificationRepo.save(emailVerification);

                    List<Users> users = usersRepo.findUserByEmail(emailVerification.getRecipientEmail());
                    ResponseTokenDto responseTokenDto = new ResponseTokenDto();
                    if (!users.isEmpty()){
                        Users user = users.get(0);
                        user.setIsRegister(true);

                        responseTokenDto.setEmail(user.getEmail());
                        responseTokenDto.setThemes(user.getCurrentTheme());

                        usersRepo.save(user);
                    }

                    responseTokenDto.setToken(jwtService.generateKey(emailVerification.getRecipientEmail()));
                    responseTokenDto.setRefreshToken(jwtService.generateKey(new HashMap<>(),emailVerification.getRecipientEmail()));

//                    Map<String, String> message = new HashMap<>();
//                    message.put("message", "You verification is successful.");

                    // Create a response object
                    ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.OK.value(), "success", List.of(responseTokenDto), true);

                    return responseDto;
                }else{

                    Map<String, String> message = new HashMap<>();
                    message.put("message", "Your verification code is mismatched.");

                    // Create a response object
                    ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.OK.value(), "failure", List.of(message), false);

                    return responseDto;
                }
            }else {
                throw new UnauthorizedUserException("Unauthorized", "Your verification code is expired please register again.");
            }
        }else {
            throw new UnauthorizedUserException("Unauthorized", "Please register first for valid verification code.");
        }


    }

    @Override
    public ResponseDto<Object> getHomeDataService() {
        Map<String, String> message = new HashMap<>();
        message.put("message", "Home page api is called.");

        // Create a response object
        ResponseDto<Object> responseDto = new ResponseDto<>(HttpStatus.OK.value(), "failure", List.of(message), false);

        return responseDto;
    }

}
