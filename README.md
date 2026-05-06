# TC1 - Sistema de Gerenciamento de Usuários e Restaurantes

## Sobre o Projeto
Este projeto foi desenvolvido como parte dos requisitos da disciplina de pós-graduação, focando na construção de uma API REST robusta, escalável e segura utilizando o ecossistema Spring. O sistema permite o gerenciamento de dois perfis distintos de usuários: **Clientes** e **Donos** de estabelecimentos, integrando autenticação via JWT e persistência em banco de dados relacional.

## Tecnologias Utilizadas
- **Linguagem:** Java 21
- **Framework Principal:** Spring Boot 3.5.13
- **Persistência de Dados:** Spring Data JPA
- **Banco de Dados:** PostgreSQL 16
- **Migração de Dados:** Flyway
- **Segurança:** Spring Security & JWT (JSON Web Token)
- **Documentação:** SpringDoc OpenAPI (Swagger UI)
- **Containerização:** Docker & Docker Compose
- **Gestão de Dependências:** Maven

## Arquitetura e Padrões
O projeto segue os princípios da arquitetura em camadas e boas práticas de desenvolvimento:
- **Camada de Controller:** Exposição dos endpoints REST e validação de entrada (Bean Validation).
- **Camada de Service:** Encapsulamento da lógica de negócio.
- **Camada de Repository:** Interface de comunicação com o banco de dados via JPA.
- **DTO (Data Transfer Objects):** Utilização de `records` Java para transferência de dados segura e imutável.
- **Global Exception Handling:** Tratamento centralizado de erros utilizando `ProblemDetail` (RFC 7807).

## Requisitos Prévios
- Docker e Docker Compose instalados.
- Java 21 (opcional, se rodar via Docker).
- Maven 3.9+ (opcional, se rodar localmente).

## Como Executar

### 1. Via Docker Compose (Recomendado)
Para subir o ambiente completo (Aplicação + Banco de Dados):
```bash
docker-compose up -d
```
A aplicação estará disponível em `http://localhost:8080`.

### 2. Execução Local (Desenvolvimento)
Certifique-se de que o banco PostgreSQL esteja rodando e configure as variáveis de ambiente no `application.properties` ou exporte-as no terminal:
```bash
mvn spring-boot:run
```

## Documentação da API

### Swagger UI
A documentação interativa da API (Swagger) pode ser acessada em:
`http://localhost:8080/swagger-ui.html`

### Exemplos de Requisições
Para exemplos práticos de como interagir com a API via `curl`, consulte o arquivo:
[requisicoes.md](requisicoes.md)

## Segurança e Autenticação
A API utiliza **JWT (JSON Web Token)** para autenticação.
1. O usuário deve realizar o login em `/api/v1/auth/login`.
2. O token retornado deve ser enviado em todas as requisições protegidas através do cabeçalho:
   `Authorization: Bearer <seu_token>`

## Estrutura do Banco de Dados
As migrações são gerenciadas pelo Flyway. Os scripts SQL podem ser encontrados em:
`src/main/resources/db/migration`

- `V1`: Criação das tabelas de usuários e endereços.
- `V2`: Criação de índices para otimização de busca.
- `V3`: Inserção de dados de teste.

---
Desenvolvido como projeto acadêmico para fins de estudo em Pós-Graduação.
