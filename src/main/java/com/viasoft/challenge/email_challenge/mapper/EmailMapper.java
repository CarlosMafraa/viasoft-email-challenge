package com.viasoft.challenge.email_challenge.mapper;

import com.viasoft.challenge.email_challenge.dto.EmailAwsDTO;
import com.viasoft.challenge.email_challenge.dto.EmailOciDTO;
import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;

public class EmailMapper {
    public static EmailAwsDTO toAws(EmailRequestDTO dto) {
        EmailAwsDTO aws = new EmailAwsDTO();
        aws.setRecipient(dto.getRecipientEmail());
        aws.setRecipientName(dto.getRecipientName());
        aws.setSender(dto.getSenderEmail());
        aws.setSubject(dto.getSubject());
        aws.setContent(dto.getContent());
        return aws;
    }

    public static EmailOciDTO toOci(EmailRequestDTO dto) {
        EmailOciDTO oci = new EmailOciDTO();
        oci.setRecipientEmail(dto.getRecipientEmail());
        oci.setRecipientName(dto.getRecipientName());
        oci.setSenderEmail(dto.getSenderEmail());
        oci.setSubject(dto.getSubject());
        oci.setBody(dto.getContent());
        return oci;
    }
}
