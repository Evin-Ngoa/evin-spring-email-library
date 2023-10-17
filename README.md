# Evin Email Library

Evin Library is a customizable Java library for sending emails with support for HTML content, attachments, and configurable retry mechanisms. It simplifies the process of integrating email functionality into your Spring Boot applications.

## Features

- Send plain text or HTML-formatted emails.
- Attach files to your emails.
- Configurable email server settings.
- Retry failed email sending with configurable maximum retry attempts.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven or Gradle (for building and dependency management)
- A Spring Boot project where you want to use this library

### Installation

To use My Email Library in your Spring Boot project, follow these steps:

1. Add the library as a dependency in your project's build file (`pom.xml` for Maven or `build.gradle` for Gradle):

   ```xml
   <dependencies>
       <!-- Other dependencies -->
       <dependency>
           <groupId>com.evin.jebes</groupId>
           <artifactId>evin-spring-email-library</artifactId>
           <version>1.0.0</version>
       </dependency>
   </dependencies>
   ```
2. Configure your email properties in your application.properties or application.yml file:
   - `email.host`: The SMTP server host.
   - `email.port`: The SMTP server port.
   - `email.username`: The email account username.
   - `email.password`: The email account password.
   - `email.retry.maxAttempts`: The maximum number of retry attempts for sending an email.
```properties
# Email Configuration
email.host=smtp.example.com
email.port=587
email.username=your_email@example.com
email.password=your_email_password
email.retry.maxAttempts=3
```

3. Inject and use the EmailService bean in your Spring components to send emails. Example usage:
```java
import com.evin.jebes.evinspringemaillibrary.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MyController {

    private final EmailService emailService;

    @Autowired
    public MyController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
        emailService.sendHtmlEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getHtmlContent(), emailRequest.getAttachmentPath());
    }
}

```
4. To create the `EmailRequest` model with the necessary properties for sending an email, you can define it like this in Java: 

With Lombok
```java
@Data
@AllArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String htmlContent;
    private String attachmentPath; // Optional, if you want to support attachments
}
```

Without Lombok
```java
public class EmailRequest {
    private String to;
    private String subject;
    private String htmlContent;
    private String attachmentPath; // Optional, if you want to support attachments

    // Constructors, getters, and setters

    public EmailRequest() {
    }

    public EmailRequest(String to, String subject, String htmlContent, String attachmentPath) {
        this.to = to;
        this.subject = subject;
        this.htmlContent = htmlContent;
        this.attachmentPath = attachmentPath;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
}

```