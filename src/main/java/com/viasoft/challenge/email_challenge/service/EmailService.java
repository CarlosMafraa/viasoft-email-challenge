package com.viasoft.challenge.email_challenge.service;

import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import com.viasoft.challenge.email_challenge.service.strategy.EmailStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {

    @Value("${mail.integracao}")
    private String integracao;

    private final Map<String, EmailStrategy> strategies;

    public EmailService(Map<String, EmailStrategy> strategies) {
        this.strategies = strategies;
    }

    public void processEmail(EmailRequestDTO input) throws Exception {
        EmailStrategy strategy = strategies.get(integracao.toUpperCase());

        if (strategy == null) {
            throw new IllegalArgumentException("Integração inválida: " + integracao);
        }

        String json = strategy.processEmail(input);
        System.out.println("JSON gerado: " + json);
    }
}

