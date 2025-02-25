package com.socialmedia.service.abstraction;

import com.socialmedia.dto.EmailDetails;
import jakarta.mail.MessagingException;

public interface EmailService {

    public void sendSimpleMail(EmailDetails details) throws MessagingException;

    public void sendMailWithAttachment(EmailDetails details) throws MessagingException;

}
