package com.wyzard.owlery.payload.request;
import lombok.Data;

import java.util.Map;

@Data
public class TransactionalEmailRequest {

    private String recipientEmail;
    private String enumType;
    private Map<String, String> placeholders;

}

