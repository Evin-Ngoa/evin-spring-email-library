package com.evin.jebes.evinspringemaillibrary.util;
import com.evin.jebes.evinspringemaillibrary.config.EmailRetryConfig;
import com.evin.jebes.evinspringemaillibrary.util.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private EmailRetryConfig emailRetryConfig;
    private EmailServiceImpl emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks

        // Create an instance of EmailService with the mocked JavaMailSender
        emailService = new EmailServiceImpl(javaMailSender, emailRetryConfig);

    }

    @Test
    public void testSendHtmlEmail() throws MessagingException {
        // Arrange
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String htmlContent = "<html><body><h1>Hello, World!</h1></body></html>";
        String attachmentPath = "/path/to/attachment.pdf";

        // Configure the emailRetryConfig mock
        Mockito.when(emailRetryConfig.getMaxAttempts()).thenReturn(3);

        // Act
        emailService.sendHtmlEmail(to, subject, htmlContent, attachmentPath);


        // Assert
        // Verify the send method with Mockito.any() and casting
        Mockito.verify(javaMailSender, Mockito.times(1)).send((MimeMessage) Mockito.any());

    }
}

