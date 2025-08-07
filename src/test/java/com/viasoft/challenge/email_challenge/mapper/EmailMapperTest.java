package com.viasoft.challenge.email_challenge.mapper;

import com.viasoft.challenge.email_challenge.dto.EmailAwsDTO;
import com.viasoft.challenge.email_challenge.dto.EmailOciDTO;
import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailMapperTest {
    @Test
    void deveConverterParaAwsCorretamente() {
        EmailRequestDTO request = new EmailRequestDTO(
                "destinatario@email.com",
                "Carlos Mafra",
                "remetente@email.com",
                "Assunto de Teste",
                "Conteúdo do e-mail para teste"
        );

        EmailAwsDTO aws = EmailMapper.toAws(request);

        assertEquals(request.getRecipientEmail(), aws.getRecipient());
        assertEquals(request.getRecipientName(), aws.getRecipientName());
        assertEquals(request.getSenderEmail(), aws.getSender());
        assertEquals(request.getSubject(), aws.getSubject());
        assertEquals(request.getContent(), aws.getContent());
    }

    @Test
    void deveConverterParaOciCorretamente() {
        EmailRequestDTO request = new EmailRequestDTO(
                "destinatario@email.com",
                "Carlos Mafra",
                "remetente@email.com",
                "Assunto de Teste",
                "Conteúdo do e-mail para teste"
        );

        EmailOciDTO oci = EmailMapper.toOci(request);

        assertEquals(request.getRecipientEmail(), oci.getRecipientEmail());
        assertEquals(request.getRecipientName(), oci.getRecipientName());
        assertEquals(request.getSenderEmail(), oci.getSenderEmail());
        assertEquals(request.getSubject(), oci.getSubject());
        assertEquals(request.getContent(), oci.getBody());
    }
}
