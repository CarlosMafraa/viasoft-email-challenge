package com.viasoft.challenge.email_challenge.service.strategy;


import com.viasoft.challenge.email_challenge.dto.EmailRequestDTO;

public interface EmailStrategy {
    String processEmail(EmailRequestDTO input) throws Exception;
}