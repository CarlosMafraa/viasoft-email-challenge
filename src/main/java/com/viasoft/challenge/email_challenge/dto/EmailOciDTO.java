package com.viasoft.challenge.email_challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class EmailOciDTO {

        @JsonProperty("recipientEmail")
        @Size(max = 40, message = "O email do destinatário deve ter no máximo 40 caracteres")
        private String recipientEmail;

        @JsonProperty("recipientName")
        @Size(max = 50, message = "O nome do destinatário deve ter no máximo 50 caracteres")
        private String recipientName;

        @JsonProperty("senderEmail")
        @Size(max = 40, message = "O email do remetente deve ter no máximo 40 caracteres")
        private String senderEmail;

        @JsonProperty("subject")
        @Size(max = 100, message = "O assunto deve ter no máximo 100 caracteres")
        private String subject;

        @JsonProperty("body")
        @Size(max = 250, message = "O conteúdo deve ter no máximo 250 caracteres")
        private String body;

        public EmailOciDTO() {
        }

        public EmailOciDTO(String recipientEmail, String recipientName, String senderEmail, String subject, String body) {
                this.recipientEmail = recipientEmail;
                this.recipientName = recipientName;
                this.senderEmail = senderEmail;
                this.subject = subject;
                this.body = body;
        }

        public @Size(max = 40, message = "O email do destinatário deve ter no máximo 40 caracteres") String getRecipientEmail() {
                return recipientEmail;
        }

        public void setRecipientEmail(@Size(max = 40, message = "O email do destinatário deve ter no máximo 40 caracteres") String recipientEmail) {
                this.recipientEmail = recipientEmail;
        }

        public @Size(max = 50, message = "O nome do destinatário deve ter no máximo 50 caracteres") String getRecipientName() {
                return recipientName;
        }

        public void setRecipientName(@Size(max = 50, message = "O nome do destinatário deve ter no máximo 50 caracteres") String recipientName) {
                this.recipientName = recipientName;
        }

        public @Size(max = 40, message = "O email do remetente deve ter no máximo 40 caracteres") String getSenderEmail() {
                return senderEmail;
        }

        public void setSenderEmail(@Size(max = 40, message = "O email do remetente deve ter no máximo 40 caracteres") String senderEmail) {
                this.senderEmail = senderEmail;
        }

        public @Size(max = 100, message = "O assunto deve ter no máximo 100 caracteres") String getSubject() {
                return subject;
        }

        public void setSubject(@Size(max = 100, message = "O assunto deve ter no máximo 100 caracteres") String subject) {
                this.subject = subject;
        }

        public @Size(max = 250, message = "O conteúdo deve ter no máximo 250 caracteres") String getBody() {
                return body;
        }

        public void setBody(@Size(max = 250, message = "O conteúdo deve ter no máximo 250 caracteres") String body) {
                this.body = body;
        }
}