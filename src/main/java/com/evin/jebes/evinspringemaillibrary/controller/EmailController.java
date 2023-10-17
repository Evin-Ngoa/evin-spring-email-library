package com.evin.jebes.evinspringemaillibrary.controller;

import com.evin.jebes.evinspringemaillibrary.dto.ApiResponse;
import com.evin.jebes.evinspringemaillibrary.dto.EmailRequest;
import com.evin.jebes.evinspringemaillibrary.util.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private final EmailService emailService;

    @PostMapping("/send-email")
    public ApiResponse<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendHtmlEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getHtmlContent(), emailRequest.getAttachmentPath());
            return new ApiResponse<>(200, "Email Sent!", null);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ApiResponse<>(500, "Error: " + e.getMessage(), null);
        }
    }

}
