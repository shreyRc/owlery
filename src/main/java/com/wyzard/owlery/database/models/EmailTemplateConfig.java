package com.wyzard.owlery.database.models;

import com.wyzard.owlery.enums.EmailService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailTemplateConfig {

    @Id
    private String id;

    private String enumType;

    private EmailService service;

    private String templateId;

    private List<String> placeholders;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public EmailTemplateConfig(String enumType, EmailService service, String templateId, List<String> placeholders) {
        this.enumType = enumType;
        this.service = service;
        this.templateId = templateId;
        this.placeholders = placeholders.stream()
                .map(String::toUpperCase) // Convert to uppercase
                .collect(Collectors.toList());
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setPlaceholders(List<String> placeholders) {
        this.placeholders = placeholders.stream()
                .map(String::toUpperCase) // Convert to uppercase
                .collect(Collectors.toList());
    }

}
