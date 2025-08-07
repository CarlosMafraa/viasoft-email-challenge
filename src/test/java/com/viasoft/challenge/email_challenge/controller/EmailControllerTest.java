package com.viasoft.challenge.email_challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import com.viasoft.challenge.email_challenge.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
    void deveRetornar400QuandoDadosInvalidos() throws Exception {
        EmailRequestDTO requestInvalido = new EmailRequestDTO(
                "", "", "emailinvalido", "", "");

        mockMvc.perform(post("/emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestInvalido)))
                .andExpect(status().isBadRequest());
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

