package com.evin.jebes.evinspringemaillibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String htmlContent;
    private String attachmentPath; // Optional, if you want to support attachments
}
