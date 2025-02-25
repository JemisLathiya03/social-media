package com.socialmedia.controller;

import com.socialmedia.dto.ResponseDto;
import com.socialmedia.dto.ResponseTokenDto;
import com.socialmedia.dto.UsersDto;
import com.socialmedia.dto.VerificationCodeDto;
import com.socialmedia.modal.Users;
import com.socialmedia.service.abstraction.AuthUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Arrays;

@RestController
@CrossOrigin
@Tag(name = "User Registration API", description = "Users register and login")
public class AuthController {

    Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthUserDetailService authUserDetailService;

    @PostMapping("/register-user")
    @Operation(summary = "User can register through this API endpoint")
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UsersDto usersDto){

        ResponseDto<Object> registerData = authUserDetailService.registerUserService(usersDto);

        return ResponseEntity.ok(registerData);
    }

    @PostMapping("/verification-code")
    @Operation(summary = "User verification is done with the help of verification code")
    public ResponseEntity<Object> verificationCode(@Valid @RequestBody VerificationCodeDto verificationCodeDto){
        ResponseDto<Object> registerData = authUserDetailService.verificationCodeService(verificationCodeDto);

        return ResponseEntity.ok(registerData);
    }

    @SecurityRequirement(name = "Bearer Authentication") // Secure all endpoints in this controller
    @GetMapping("/get-home-data")
    @Operation(summary = "User get home page data using this api")
    public ResponseEntity<Object> getHomeData(){
        ResponseDto<Object> registerData = authUserDetailService.getHomeDataService();

        return ResponseEntity.ok(registerData);
    }

    @GetMapping("/")
    public String greeting(HttpServletRequest request){
        return "Welcome to spring boot with spring security.";
    }


}
