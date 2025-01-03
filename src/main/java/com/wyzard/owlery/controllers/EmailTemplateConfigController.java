package com.wyzard.owlery.controllers;
import com.wyzard.owlery.database.models.EmailTemplateConfig;
import com.wyzard.owlery.enums.EmailService;
import com.wyzard.owlery.services.EmailTemplateConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template-config")
public class EmailTemplateConfigController {

    @Autowired
    private EmailTemplateConfigService emailTemplateConfigService;

    @PostMapping
    public ResponseEntity<EmailTemplateConfig> createTemplateConfig(
            @RequestParam String enumType,
            @RequestParam EmailService service,
            @RequestParam String templateId,
            @RequestBody List<String> placeholders) {
        return ResponseEntity.ok(emailTemplateConfigService.createTemplateConfig(enumType, service, templateId, placeholders));
    }

    @GetMapping("/{enumType}")
    public ResponseEntity<EmailTemplateConfig> getTemplateConfig(@PathVariable String enumType) {
        return emailTemplateConfigService.getTemplateConfigByEnumType(enumType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{enumType}")
    public ResponseEntity<EmailTemplateConfig> updateTemplateConfig(
            @PathVariable String enumType,
            @RequestParam EmailService service,
            @RequestParam String templateId,
            @RequestBody List<String> placeholders) {
        return ResponseEntity.ok(emailTemplateConfigService.updateTemplateConfig(enumType, service, templateId, placeholders));
    }

    @DeleteMapping("/{enumType}")
    public ResponseEntity<Void> deleteTemplateConfig(@PathVariable String enumType) {
        emailTemplateConfigService.deleteTemplateConfig(enumType);
        return ResponseEntity.noContent().build();
    }
}
