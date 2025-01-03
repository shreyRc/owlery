package com.wyzard.owlery.database.repositories;

import com.wyzard.owlery.database.models.EmailTemplateConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmailTemplateConfigRepository extends MongoRepository<EmailTemplateConfig, String> {

    Optional<EmailTemplateConfig> findByEnumType(String enumType);
}