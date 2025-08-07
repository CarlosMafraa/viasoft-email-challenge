# Desafio Back-end Viasoft - API de Envio de E-mail

Obrigado pelo interesse em avaliar este projeto! Este é o código desenvolvido para o desafio técnico do back-end da Viasoft.

---

## 📋 Sobre o Projeto

Esta aplicação é uma API REST desenvolvida em **Java 17** utilizando **Spring Boot**, que recebe requisições para envio de e-mail e processa o objeto recebido para duas integrações fictícias: **AWS** e **OCI**.

O objetivo principal é receber um objeto padrão de e-mail, adaptá-lo para os formatos específicos das integrações, serializá-lo para JSON e imprimi-lo no console, simulando o envio.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (Spring Web)
- **Maven**
- **Jakarta Validation** (`@Size`, `@Valid`)
- **Jackson** (serialização JSON)
- **JUnit 5 + Mockito** (testes unitários)

---

## 🏗️ Arquitetura e Organização

- **DTOs:** Classes para transporte dos dados do e-mail (`EmailRequestDTO`, `EmailAwsDTO`, `EmailOciDTO`).
- **Controller:** Exposição do endpoint REST para receber requisições POST em `/emails`.
- **Service:** Processamento do e-mail, utilizando o padrão **Strategy** para selecionar a integração (AWS ou OCI) via configuração.
- **Strategy:** Implementações separadas para AWS e OCI, adaptando o objeto e validando os dados específicos.
- **Validação:** Uso de Jakarta Validation para verificar o tamanho dos campos nos DTOs, com tratamento global de erros retornando status 400 e mensagens amigáveis.
- **Tratamento de Exceções:** Controle de erros 400 e 500 no controller.
- **Testes:** Testes unitários para as estratégias e o serviço principal.

---

## ⚙️ Configuração

No arquivo `src/main/resources/application.properties`, configure a integração desejada:

```properties
mail.integracao=AWS
# ou
mail.integracao=OCI
```

🚀 Como Rodar Localmente
Siga os passos abaixo para executar o projeto em sua máquina:


1. 📥 Clone o repositório:
```bash
git clone https://github.com/CarlosMafraa/viasoft-email-challenge.git
cd viasoft-email-challenge
```

2. 🛠️ Compile e rode a aplicação:
```bash
mvn clean install
mvn spring-boot:run
```

3. 🌐 Acesse a documentação Swagger:
```bash
Abra o navegador e visite:
texthttp://localhost:8080/swagger-ui/index.html
```

## 🌍 Aplicação em Produção

Acesse a API implantada no Render:

- **Documentação Swagger**: [https://viasoft-email-challenge.onrender.com/swagger-ui/index.html#/Email%20API/processEmail](https://viasoft-email-challenge.onrender.com/swagger-ui/index.html#/Email%20API/processEmail)

