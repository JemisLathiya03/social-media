package com.socialmedia.service.implementation;

import com.socialmedia.dto.EmailDetails;
import com.socialmedia.service.abstraction.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;


    @Override
    public void sendSimpleMail(EmailDetails details) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(sender);
        helper.setTo(details.getRecipient());
        helper.setSubject(details.getSubject());
        helper.setText(details.getMsgBody(), true); // `true` enables HTML content

        javaMailSender.send(message);
    }

    @Override
    public void sendMailWithAttachment(EmailDetails details) throws MessagingException {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(details.getRecipient());
        mimeMessageHelper.setText(details.getMsgBody());
        mimeMessageHelper.setSubject(
                details.getSubject());

        // Adding the attachment
        FileSystemResource file
                = new FileSystemResource(
                new File(details.getAttachment()));

        mimeMessageHelper.addAttachment(
                file.getFilename(), file);

        // Sending the mail
        javaMailSender.send(mimeMessage);
    }
}
