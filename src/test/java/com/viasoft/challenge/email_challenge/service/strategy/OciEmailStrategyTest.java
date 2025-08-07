package com.viasoft.challenge.email_challenge.service.strategy;

import com.viasoft.challenge.email_challenge.dto.EmailOciDTO;
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
import static org.mockito.Mockito.*;

public class OciEmailStrategyTest {

    private Validator validator;
    private OciEmailStrategy strategy;

    @BeforeEach
    void setUp() {
        validator = mock(Validator.class);
        strategy = new OciEmailStrategy(validator);
    }

    @Test
    void deveRetornarJsonValidoQuandoNaoHaViolacoes() throws Exception {
        EmailRequestDTO input = new EmailRequestDTO("email@teste.com", "Nome", "sender@teste.com", "Assunto", "Conteúdo");

        when(validator.validate(any(EmailOciDTO.class))).thenReturn(Collections.emptySet());

        String result = strategy.processEmail(input);

        assertNotNull(result);

        assertTrue(result.contains("\"recipientEmail\""));
    }

    @Test
    void deveLancarExcecaoQuandoHaViolacoes() {
        EmailRequestDTO input = new EmailRequestDTO("email inválido", "Nome", "sender@teste.com", "Assunto", "Conteúdo");

        ConstraintViolation<EmailOciDTO> violation = mock(ConstraintViolation.class);

        Set<ConstraintViolation<EmailOciDTO>> violations = Set.of(violation);

        when(validator.validate(any(EmailOciDTO.class))).thenReturn(violations);

        assertThrows(ConstraintViolationException.class, () -> strategy.processEmail(input));
    }
}
