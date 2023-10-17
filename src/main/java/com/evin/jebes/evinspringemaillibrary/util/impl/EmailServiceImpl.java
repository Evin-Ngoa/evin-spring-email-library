package com.evin.jebes.evinspringemaillibrary.util.impl;

import com.evin.jebes.evinspringemaillibrary.config.EmailRetryConfig;
import com.evin.jebes.evinspringemaillibrary.util.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRetryConfig retryConfig;
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Retryable(maxAttemptsExpression = "#{@emailRetryConfig.getMaxAttempts}", backoff = @Backoff(delay = 1000))
    public void sendHtmlEmail(String to, String subject, String htmlContent, String attachmentPath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        logger.info("Sending email to: {}", to);

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Set the second parameter to true to indicate HTML content

            if (attachmentPath != null) {
                helper.addAttachment("Attachment", new File(attachmentPath));
            }

            javaMailSender.send(mimeMessage);
            logger.info("Email sent successfully.");
        } catch (MessagingException e) {
            logger.error("Error sending email: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
