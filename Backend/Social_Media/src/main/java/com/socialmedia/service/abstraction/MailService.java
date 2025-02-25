package com.socialmedia.service.abstraction;

import jakarta.mail.MessagingException;

public interface MailService {

    public void sendEmail(String to, String subject, String text) throws MessagingException;
}
