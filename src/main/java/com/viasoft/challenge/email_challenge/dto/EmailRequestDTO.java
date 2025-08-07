package com.viasoft.challenge.email_challenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailRequestDTO {

    private String recipientEmail;

    private String recipientName;

    private String senderEmail;

    private String subject;

    private String content;

    public EmailRequestDTO() {
    }

    public EmailRequestDTO(String recipientEmail, String recipientName, String senderEmail, String subject, String content) {
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.content = content;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //    public EmailRequestDTO() {
//    }
//
//        public EmailRequestDTO(String recipientEmail, String recipientName, String senderEmail, String subject, String content) {
//                this.recipientEmail = recipientEmail;
//                this.recipientName = recipientName;
//                this.senderEmail = senderEmail;
//                this.subject = subject;
//                this.content = content;
//        }
//
//        public @NotBlank(message = "O email do destinatário é obrigatório") @Email(message = "O email do destinatário deve ser válido") String getRecipientEmail() {
//                return recipientEmail;
//        }
//
//        public void setRecipientEmail(@NotBlank(message = "O email do destinatário é obrigatório") @Email(message = "O email do destinatário deve ser válido") String recipientEmail) {
//                this.recipientEmail = recipientEmail;
//        }
//
//        public @NotBlank(message = "O nome do destinatário é obrigatório")  String getRecipientName() {
//                return recipientName;
//        }
//
//        public void setRecipientName(@NotBlank(message = "O nome do destinatário é obrigatório") String recipientName) {
//                this.recipientName = recipientName;
//        }
//
//        public @NotBlank(message = "O email do remetente é obrigatório") @Email(message = "O email do remetente deve ser válido") String getSenderEmail() {
//                return senderEmail;
//        }
//
//        public void setSenderEmail(@NotBlank(message = "O email do remetente é obrigatório") @Email(message = "O email do remetente deve ser válido") String senderEmail) {
//                this.senderEmail = senderEmail;
//        }
//
//        public @NotBlank(message = "O assunto é obrigatório") String getSubject() {
//                return subject;
//        }
//
//        public void setSubject(@NotBlank(message = "O assunto é obrigatório") String subject) {
//                this.subject = subject;
//        }
//
//        public @NotBlank(message = "O conteúdo é obrigatório") String getContent() {
//                return content;
//        }
//
//        public void setContent(@NotBlank(message = "O conteúdo é obrigatório") String content) {
//                this.content = content;
//        }
}
