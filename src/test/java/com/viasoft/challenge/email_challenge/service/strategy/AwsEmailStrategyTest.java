package com.viasoft.challenge.email_challenge.service.strategy;

import com.viasoft.challenge.email_challenge.dto.EmailAwsDTO;
import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AwsEmailStrategyTest {

    private Validator validator;
    private AwsEmailStrategy strategy;

    @BeforeEach
    void setUp() {
        validator = mock(Validator.class);
        strategy = new AwsEmailStrategy(validator);
    }

    @Test
    void deveRetornarJsonValidoQuandoNaoHaViolacoes() throws Exception {
        EmailRequestDTO input = new EmailRequestDTO("email@teste.com", "Nome", "sender@teste.com", "Assunto", "Conteúdo");

        when(validator.validate(any(EmailAwsDTO.class))).thenReturn(Collections.emptySet());

        String result = strategy.processEmail(input);

        assertNotNull(result);
        assertTrue(result.contains("\"recipient\""));
    }

    @Test
    void deveLancarExcecaoQuandoHaViolacoes() {
        EmailRequestDTO input = new EmailRequestDTO("email inválido", "Nome", "sender@teste.com", "Assunto", "Conteúdo");

        ConstraintViolation<EmailAwsDTO> violation = mock(ConstraintViolation.class);
        Set<ConstraintViolation<EmailAwsDTO>> violations = Set.of(violation);
        when(validator.validate(any(EmailAwsDTO.class))).thenReturn(violations);

        assertThrows(ConstraintViolationException.class, () -> strategy.processEmail(input));
    }
}
