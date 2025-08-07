package com.viasoft.challenge.email_challenge.controller;

import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import com.viasoft.challenge.email_challenge.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
@Tag(name = "Email API", description = "API for processing email requests")
public class EmailController {

    public EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    @Operation(summary = "Processar requisição de e-mail",
            description = "Processa a requisição de e-mail de acordo com o tipo de integração configurado nas propriedades")
    @ApiResponse(responseCode = "204", description = "E-mail processado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos!")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor!")
    public ResponseEntity<Object> processEmail(@Valid @RequestBody EmailRequestDTO emailRequest) {
        try {
            emailService.processEmail(emailRequest);
            return ResponseEntity.noContent().build();
        } catch (ConstraintViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno no servidor");
        }
    }
}