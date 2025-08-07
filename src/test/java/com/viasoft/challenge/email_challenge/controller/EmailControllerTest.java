package com.viasoft.challenge.email_challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.challenge.email_challenge.dto.EmailAwsDTO;
import com.viasoft.challenge.email_challenge.dto.EmailOciDTO;
import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import com.viasoft.challenge.email_challenge.mapper.EmailMapper;
import com.viasoft.challenge.email_challenge.service.EmailService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmailController.class)
public class EmailControllerTest {

    @MockitoBean
    private EmailService emailService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private EmailRequestDTO buildValidRequest() {
        return new EmailRequestDTO(
                "destinatario@email.com",
                "Carlos Mafra",
                "remetente@email.com",
                "Assunto de Teste",
                "Conteúdo do e-mail para teste"
        );
    }

    @Test
    void deveRetornar204QuandoEmailValido() throws Exception {
        mockMvc.perform(post("/emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildValidRequest())))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveValidarAwsDtoComDadosInvalidos() {
        EmailRequestDTO requestInvalido = new EmailRequestDTO(
                "Nome muito muito muito muito muito muito muito muito muito muito muito muito grande",
                "destinatario@exemplo.com",
                "remetente@exemplo.com",
                "Assunto",
                "Corpo do e-mail"
        );

        EmailAwsDTO awsDto = EmailMapper.toAws(requestInvalido);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(awsDto);

        assertFalse(violations.isEmpty());
    }


    @Test
    void deveRetornar500QuandoServiceLancarExcecao() throws Exception {
        EmailRequestDTO request = buildValidRequest();

        doThrow(new RuntimeException("Erro interno"))
                .when(emailService).processEmail(any());

        mockMvc.perform(post("/emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deveRetornar400_QuandoServiceLancarIllegalArgumentException() throws Exception {
        EmailRequestDTO request = buildValidRequest();

        doThrow(new IllegalArgumentException("Erro de validação"))
                .when(emailService).processEmail(any());

        mockMvc.perform(post("/emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}

