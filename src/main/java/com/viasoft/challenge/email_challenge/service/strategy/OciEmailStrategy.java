package com.viasoft.challenge.email_challenge.service.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.challenge.email_challenge.dto.EmailAwsDTO;
import com.viasoft.challenge.email_challenge.dto.EmailOciDTO;
import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import com.viasoft.challenge.email_challenge.mapper.EmailMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("OCI")
public class OciEmailStrategy implements EmailStrategy {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Validator validator;

    @Autowired
    public OciEmailStrategy(Validator validator) {
        this.validator = validator;
    }

    @Override
    public String processEmail(EmailRequestDTO input) throws Exception {
        System.out.println("Usando estratégia OCI");
        EmailOciDTO ociDto = EmailMapper.toOci(input);

        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(ociDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return mapper.writeValueAsString(ociDto);
    }
}

