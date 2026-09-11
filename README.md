# API de Beneficiários e Documentos

API REST desenvolvida com **Spring Boot 4** para cadastro de beneficiários de planos de saúde e seus documentos, com as operações de **CRUD de beneficiário** e **listagem de documentos por beneficiário**.

## Stack

| Tecnologia | Versão |
|---|---|
| Java | 17 (recomendado JDK 21 em runtime) |
| Spring Boot | 4.1.1 |
| Spring Data JPA / Hibernate | via Boot |
| Flyway | via Boot (`spring-boot-starter-flyway`) |
| PostgreSQL | 17 (via Docker) |
| Lombok, Bean Validation | via Boot |
| springdoc-openapi (Swagger UI) | 3.1.0 |
| Spotless (Google Java Format) | 2.44.4 |
| Maven | Wrapper (`mvnw`) |

## Arquitetura

O projeto segue o padrão de **portas e adaptadores** (estilo hexagonal) em camadas, mantendo o domínio desacoplado do framework e da infraestrutura:

```
HTTP → Controller (application/api) → Service → Porta (repository)
                                                       ↓
                                          InfraRepository (infra)
                                                       ↓
                                          JpaRepository (Spring Data)
                                                       ↓
                                                    PostgreSQL
```

- **`domain`** — entidades (`Beneficiario`, `Documento`, `TipoDocumento`) com comportamento, sem dependência do Spring.
- **`application/api`** — interfaces REST, controller e DTOs de request/response.
- **`service`** — regras de negócio e transações.
- **`repository`** — "portas" (interfaces) que o service conhece.
- **`infra`** — adaptadores JPA que implementam as portas (`BeneficiarioJPARepository`, `BeneficiarioInfraRepository`).
- **`handler`** — tratamento global de erros (`APIException`, `ErrorCode`, `ErrorApiResponse`, `MessageUtil`).

### Estrutura de pastas

```
teste/
├── docker-compose.yml                # PostgreSQL local
└── teste/                            # módulo Maven da aplicação
    ├── mvnw / mvnw.cmd
    ├── pom.xml
    └── src/main
        ├── java/com/ekan/teste
        │   ├── beneficiario/
        │   │   ├── application/api/{request,response}
        │   │   ├── domain/           # Beneficiario
        │   │   ├── infra/            # BeneficiarioJPARepository, BeneficiarioInfraRepository
        │   │   ├── repository/       # BeneficiarioRepository (porta)
        │   │   └── service/          # BeneficiarioService, BeneficiarioApplicationService
        │   ├── documento/
        │   │   ├── application/api/{request,response}
        │   │   └── domain/           # Documento, TipoDocumento
        │   └── handler/              # erros e mensagens
        └── resources
            ├── application.yml
            ├── messages.properties
            └── db/migration/         # V1, V2 (Flyway)
```

## Funcionalidades

- Cadastrar beneficiário **junto com seus documentos** (`@OneToMany(cascade = ALL, orphanRemoval = true)`).
- Listar beneficiários com documentos, **paginado**.
- Listar documentos de um beneficiário por id, **paginado**.
- Atualizar dados cadastrais (nome, telefone, data de nascimento).
- Remover beneficiário (remove também os documentos via cascade).
- Migrações de banco via **Flyway**.
- Documentação automática via **Swagger UI**.
- Tratamento de erros padronizado com mensagens em `messages.properties`.

## Endpoints

Base URL: `http://localhost:8080/ekanTest/api`

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/v1/beneficiario` | Cadastra beneficiário com documentos |
| `GET` | `/v1/beneficiario/beneficiarios?page=0&size=10` | Lista beneficiários paginados |
| `GET` | `/v1/beneficiario/{id}/documentos?page=0&size=10` | Lista documentos do beneficiário |
| `PUT` | `/v1/beneficiario/{id}` | Atualiza dados cadastrais |
| `DELETE` | `/v1/beneficiario/{id}` | Remove beneficiário (204) |

Swagger UI: `http://localhost:8080/ekanTest/api/swagger-ui.html`
OpenAPI JSON: `http://localhost:8080/ekanTest/api/v3/api-docs`

### Exemplo — Criar beneficiário

```json
POST /v1/beneficiario
{
  "nome": "Joao Silva",
  "telefone": "+55 11 98765-4321",
  "dataNascimento": "1990-05-20",
  "documento": [
    { "tipoDocumento": "RG", "descricao": "12.345.678-9" },
    { "tipoDocumento": "CPF", "descricao": "123.456.789-00" }
  ]
}
```

`tipoDocumento` é um enum persistido como string: `RG`, `CPF`, `CNH`, `PASSAPORTE`.

### Exemplo — Atualizar dados cadastrais

```json
PUT /v1/beneficiario/{id}
{
  "nome": "Joao Silva",
  "telefone": "+55 11 99999-9999",
  "dataNascimento": "1990-05-20"
}
```

### Exemplo — Resposta da listagem paginada

```json
{
  "content": [ { "idBeneficiario": "...", "nome": "...", "telefone": "...", "dataNascimento": "...", "documento": [ ... ] } ],
  "totalElements": 1,
  "paginaAtual": 0,
  "totalPaginas": 1,
  "totalUsuarios": 1
}
```

## Banco de dados

- Banco: `testdb`, usuário/senha `postgres/postgres`, porta `5432`, schema `test`.
- As tabelas são criadas pela migrações Flyway:

| Versão | Arquivo | Cria |
|---|---|---|
| V1 | `V1__criar_tabela_beneficiario.sql` | tabela `beneficiario` |
| V2 | `V2__criar_tabela_documento.sql` | tabela `documento` |

- `spring.jpa.hibernate.ddl-auto: none` — o Hibernate **não** altera o schema; quem cuida disso é o Flyway.
- Configurações de banco podem ser sobrescritas por variáveis de ambiente (`DATASOURCE_URL`, `DATASOURCE_USER`, `DATASOURCE_PASS`).

## Como executar

### Pré-requisitos

- JDK 17+ (recomendado 21)
- Docker (para o PostgreSQL)
- (Opcional) Maven — o projeto já possui o **Maven Wrapper** (`mvnw`)

### 1. Subir o banco

Execute a partir da **raiz do repositório** (onde está o `docker-compose.yml`):

```bash
docker compose up -d
```

> A aplicação tem `spring.docker.compose.enabled: false`, então o banco deve ser iniciado manualmente.

### 2. Compilar (build)

Linux/macOS:

```bash
./mvnw clean compile
```

Windows:

```powershell
.\mvnw.cmd clean compile
```

### 3. Rodar a aplicação

```bash
./mvnw spring-boot:run
```

ou com o JAR:

```bash
./mvnw clean package
java -jar target/teste-0.0.1-SNAPSHOT.jar
```

### 4. Testar

Acesse o Swagger em `http://localhost:8080/ekanTest/api/swagger-ui.html` ou use os endpoints acima.

## Formatação de código

O projeto usa o **Spotless** com Google Java Format. Para formatar antes de commitar:

```bash
./mvnw spotless:apply
```

Windows:

```powershell
.\mvnw.cmd spotless:apply
```

## Tratamento de erros

- Erros de negócio são lançados como `APIException` com um `ErrorCode` (`beneficiario.nao.encontrado`, etc.).
- O `RestResponseEntityExceptionHandler` (handler global) converte em JSON padronizado (`ErrorApiResponse`) com status HTTP correto.
- As mensagens ficam em `src/main/resources/messages.properties`.
- Exemplo: `GET /v1/beneficiario/{id}/documentos` com id inexistente → `404` com `"Beneficiário não encontrado"`.
