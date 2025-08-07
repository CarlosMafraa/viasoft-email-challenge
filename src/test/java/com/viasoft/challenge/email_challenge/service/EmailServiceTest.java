package com.viasoft.challenge.email_challenge.service;

import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import com.viasoft.challenge.email_challenge.service.strategy.EmailStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class EmailServiceTest {

    private EmailStrategy awsStrategy;
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        awsStrategy = mock(EmailStrategy.class);

        Map<String, EmailStrategy> strategies = Map.of("AWS", awsStrategy);

        emailService = new EmailService(strategies);

        ReflectionTestUtils.setField(emailService, "integracao", "AWS");
    }

    @Test
    void deveProcessarEmailComSucessoQuandoEstrategiaExiste() throws Exception {
        EmailRequestDTO input = new EmailRequestDTO(
                "dest@teste.com", "Nome", "remetente@teste.com", "Assunto", "Conteúdo"
        );

        when(awsStrategy.processEmail(input)).thenReturn("{json}");

        assertDoesNotThrow(() -> emailService.processEmail(input));

        verify(awsStrategy, times(1)).processEmail(input);
    }

    @Test
    void deveLancarExcecaoQuandoEstrategiaNaoExiste() {
        ReflectionTestUtils.setField(emailService, "integracao", "INVALIDA");

        EmailRequestDTO input = new EmailRequestDTO(
                "dest@teste.com", "Nome", "remetente@teste.com", "Assunto", "Conteúdo"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> emailService.processEmail(input)
        );

        assertEquals("Integração inválida: INVALIDA", exception.getMessage());
    }

}
