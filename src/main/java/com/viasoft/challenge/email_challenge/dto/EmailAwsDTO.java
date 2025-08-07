package com.viasoft.challenge.email_challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class EmailAwsDTO {

        @JsonProperty("recipient")
        @Size(max = 45, message = "O email do destinatário deve ter no máximo 45 caracteres")
        private String recipient;

        @JsonProperty("recipientName")
        @Size(max = 60, message = "O nome do destinatário deve ter no máximo 60 caracteres")
        private String recipientName;

        @JsonProperty("sender")
        @Size(max = 45, message = "O email do remetente deve ter no máximo 45 caracteres")
        private String sender;

        @JsonProperty("subject")
        @Size(max = 120, message = "O assunto deve ter no máximo 120 caracteres")
        private String subject;

        @JsonProperty("content")
        @Size(max = 256, message = "O conteúdo deve ter no máximo 256 caracteres")
        private String content;

        public EmailAwsDTO() {
        }

        public EmailAwsDTO(String recipient, String recipientName, String sender, String subject, String content) {
                this.recipient = recipient;
                this.recipientName = recipientName;
                this.sender = sender;
                this.subject = subject;
                this.content = content;
        }

        public @Size(max = 45, message = "O email do destinatário deve ter no máximo 45 caracteres") String getRecipient() {
                return recipient;
        }

        public void setRecipient(@Size(max = 45, message = "O email do destinatário deve ter no máximo 45 caracteres") String recipient) {
                this.recipient = recipient;
        }

        public @Size(max = 60, message = "O nome do destinatário deve ter no máximo 60 caracteres") String getRecipientName() {
                return recipientName;
        }

        public void setRecipientName(@Size(max = 60, message = "O nome do destinatário deve ter no máximo 60 caracteres") String recipientName) {
                this.recipientName = recipientName;
        }

        public @Size(max = 45, message = "O email do remetente deve ter no máximo 45 caracteres") String getSender() {
                return sender;
        }

        public void setSender(@Size(max = 45, message = "O email do remetente deve ter no máximo 45 caracteres") String sender) {
                this.sender = sender;
        }

        public @Size(max = 120, message = "O assunto deve ter no máximo 120 caracteres") String getSubject() {
                return subject;
        }

        public void setSubject(@Size(max = 120, message = "O assunto deve ter no máximo 120 caracteres") String subject) {
                this.subject = subject;
        }

        public @Size(max = 256, message = "O conteúdo deve ter no máximo 256 caracteres") String getContent() {
                return content;
        }

        public void setContent(@Size(max = 256, message = "O conteúdo deve ter no máximo 256 caracteres") String content) {
                this.content = content;
        }
}
