package com.evin.jebes.evinspringemaillibrary.util;

import jakarta.mail.MessagingException;

@FunctionalInterface
public interface EmailService {
    void sendHtmlEmail(String to, String subject, String htmlContent, String attachmentPath) throws MessagingException;
}
