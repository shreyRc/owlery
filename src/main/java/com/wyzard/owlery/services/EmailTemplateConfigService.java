package com.wyzard.owlery.services;
import com.wyzard.owlery.database.models.EmailTemplateConfig;
import com.wyzard.owlery.database.repositories.EmailTemplateConfigRepository;
import com.wyzard.owlery.enums.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailTemplateConfigService {

    private final EmailTemplateConfigRepository repository;

    @Autowired
    public EmailTemplateConfigService(EmailTemplateConfigRepository repository) {
        this.repository = repository;
    }

    public EmailTemplateConfig createTemplateConfig(String enumType, EmailService service, String templateId, List<String> placeholders) {
        List<String> uppercasePlaceholders = placeholders.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        EmailTemplateConfig config = new EmailTemplateConfig(enumType, service, templateId, uppercasePlaceholders);
        return repository.save(config);
    }

    public Optional<EmailTemplateConfig> getTemplateConfigByEnumType(String enumType) {
        return repository.findByEnumType(enumType);
    }

    public EmailTemplateConfig updateTemplateConfig(String enumType, EmailService service, String templateId, List<String> placeholders) {
        Optional<EmailTemplateConfig> existingConfig = repository.findByEnumType(enumType);
        if (existingConfig.isPresent()) {
            EmailTemplateConfig config = existingConfig.get();
            config.setService(service);
            config.setTemplateId(templateId);
            config.setPlaceholders(placeholders);
            config.setUpdatedAt(LocalDateTime.now());
            return repository.save(config);
        }
        throw new IllegalArgumentException("Config not found for enum type: " + enumType);
    }

    public void deleteTemplateConfig(String enumType) {
        repository.findByEnumType(enumType).ifPresent(repository::delete);
    }
}
