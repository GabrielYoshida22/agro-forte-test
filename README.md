# Arquitetura do Projeto AgroForte

## Visão Geral

O projeto AgroForte segue a **Arquitetura Hexagonal** (Ports & Adapters), que visa isolar o núcleo de negócio da aplicação das dependências externas, facilitando a manutenção, testabilidade e flexibilidade do sistema.

A seguir, a descrição das principais camadas do projeto, suas responsabilidades e exemplos baseados na estrutura atual.

---

## Camadas do Projeto

### 1. Core / Domain (`core/domain`)

- **Responsabilidade:**  
  Esta é a camada do domínio da aplicação, onde residem as regras de negócio, modelos principais do sistema.  
  Aqui ficam as classes que representam o modelo conceitual do negócio, sem dependências de frameworks ou infraestrutura.

- **O que incluir:**
    - Entidades de negócio (ex: `PersonPhysicalDomain`)
    - Objetos de valor e enums (ex: `Gender`, `MaritalStatus`)
    - Lógica de validação e regras do domínio
    - Interfaces (ports) que representam operações essenciais que o domínio precisa realizar e que serão implementadas externamente

- **Importante:**  
  Esta camada deve ser independente de frameworks e tecnologia externa.

---

### 2. Core / Service (`core/service` e `core/service/impl`)

- **Responsabilidade:**  
  Implementa a lógica de aplicação e orquestra as regras de negócio definidas no domínio.  
  Aqui ficam os serviços que coordenam operações, validam fluxos e implementam casos de uso da aplicação.

- **O que incluir:**
    - Serviços que implementam a lógica de negócio (ex: `PersonService`, `PersonServiceImpl`)
    - Coordenação entre entidades e chamadas a adaptadores externos via interfaces definidas no domínio
    - Transações, validações de negócio e regras específicas de caso de uso

- **Importante:**  
  Serviços usam o domínio, mas ainda não conhecem detalhes da infraestrutura.

---

### 3. Adapter / Inbound (`adapter/inbound`)

- **Responsabilidade:**  
  Adaptadores que recebem requisições externas e as convertem para chamadas internas ao domínio.  
  Normalmente são APIs REST, controladores, ou interfaces de usuário.

- **O que incluir:**
    - Controllers REST (ex: `PersonController`)
    - DTOs para transferência de dados (ex: `PersonPhysicalRequestDTO`)
    - Conversores entre DTOs e objetos de domínio
    - Validação inicial de dados recebidos
    - Mapeamento das requisições externas para chamadas dos serviços do domínio

- **Importante:**  
  Essa camada serve como porta de entrada da aplicação.

---

### 4. Adapter / Outbound (`adapter/outbound`)

- **Responsabilidade:**  
  Adaptadores que conectam o domínio a recursos externos, como bancos de dados, serviços externos, filas, etc.

- **O que incluir:**
    - Repositórios de persistência (ex: `PersonRepository`)
    - Entidades mapeadas para bancos (ex: `PersonPhysical`, `PersonLegal`, `Operation`, `Installment`)
    - Implementações de interfaces que o domínio usa para acessar recursos externos
    - Configurações e conexões com infraestrutura (JPA, JDBC, clientes HTTP, etc)

- **Importante:**  
  Essa camada é responsável por garantir que a aplicação possa trocar a infraestrutura sem impactar o domínio.

---

### 5. Recursos de Configuração (`src/main/resources`)

- **Responsabilidade:**  
  Arquivos de configuração da aplicação, como arquivos `.yml`, scripts SQL para migrações, etc.

- **O que incluir:**
    - Configurações de ambiente (ex: `application.yml`, `application-dev.yml`)
    - Scripts de banco de dados (ex: `V1_0_0__crate_person_physical_table.sql`)
    - Outros recursos necessários para execução da aplicação

---

## Benefícios dessa arquitetura no seu projeto

- **Isolamento do domínio:** facilita testes e manutenção.
- **Flexibilidade para trocar tecnologias:** você pode mudar banco ou frameworks sem alterar a lógica central.
- **Código mais organizado e fácil de entender.**
- **Facilidade para implementar testes unitários e de integração.**

---

# API de Cadastro de Pessoa Física

Esta API permite o cadastro de pessoas físicas no sistema, fornecendo operações para criar registros com informações pessoais básicas.

---

## Visão Geral

- **Base URL:** `http://localhost:8080`
- **Versão da API:** v0
- **Formato:** JSON
- **Tag principal:** Pessoa Física

---

## Endpoint

### Cadastrar uma nova pessoa física

- **URL:** `/pessoas/fisicas`
- **Método:** `POST`
- **Descrição:** Cadastra uma nova pessoa física no sistema.
- **Tag:** Pessoa Física

#### Request Body

O corpo da requisição deve ser enviado no formato JSON contendo os seguintes campos obrigatórios:

| Campo          | Tipo    | Descrição                        | Exemplos                    | Valores aceitos                                      |
|----------------|---------|---------------------------------|----------------------------|-----------------------------------------------------|
| `nome`         | string  | Nome completo da pessoa          | `"João da Silva"`           | Mínimo 1 caractere                                   |
| `email`        | string  | Endereço de email                | `"joao@email.com"`          | Mínimo 1 caractere                                   |
| `celular`      | string  | Número de celular                | `"11999999999"`             | Mínimo 1 caractere, formato livre                    |
| `nacionalidade`| string  | Nacionalidade da pessoa          | `"Brasileira"`              | Mínimo 1 caractere                                   |
| `cpf`          | string  | CPF da pessoa                   | `"12345678900"`             | Mínimo 1 caractere                                   |
| `rg`           | string  | RG da pessoa                    | `"1234567"`                 | Mínimo 1 caractere                                   |
| `maritalStatus`| string  | Estado civil                   | —                          | `SOLTEIRO`, `CASADO`, `DIVORCIADO`, `VIUVO`, `UNIAO_ESTAVEL` |
| `genero`       | string  | Gênero da pessoa               | —                          | `MASCULINO`, `FEMININO`, `OUTRO`                     |
| `dataNascimento`| string (date) | Data de nascimento           | `"1990-01-01"`              | Formato ISO 8601 (YYYY-MM-DD)                        |
| `nomeMae`      | string  | Nome da mãe                    | `"Maria da Silva"`          | Mínimo 1 caractere                                   |

#### Exemplo de Requisição

```bash
curl -X POST http://localhost:8080/pessoas/fisicas \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João da Silva",
    "email": "joao@email.com",
    "celular": "11999999999",
    "nacionalidade": "Brasileira",
    "cpf": "12345678900",
    "rg": "1234567",
    "maritalStatus": "SOLTEIRO",
    "genero": "MASCULINO",
    "dataNascimento": "1990-01-01",
    "nomeMae": "Maria da Silva"
  }'

```
# Descrição dos Frameworks Utilizados

Este projeto utiliza os seguintes frameworks e bibliotecas, cada um com um propósito específico para garantir uma aplicação robusta, escalável e fácil de manter:

---

## Frameworks e Bibliotecas

### 1. Spring Boot Starter Web
- Facilita a criação de aplicações web RESTful com configuração automática.
- Inclui servidor embutido (Tomcat) para execução simples.

### 2. Spring Boot Starter Data JPA
- Facilita o acesso a bancos de dados relacionais via JPA e Hibernate.
- Simplifica o mapeamento objeto-relacional e manipulação das entidades.

### 3. Spring Boot Starter Validation
- Permite validar automaticamente os dados de entrada com anotações padrão.
- Garante integridade e consistência dos dados recebidos nas requisições.

### 4. Spring Boot Starter Actuator
- Fornece endpoints para monitoramento, métricas e saúde da aplicação.
- Ajuda na operação e manutenção em ambientes de produção.

### 5. Springdoc OpenAPI Starter WebMVC UI
- Gera documentação automática da API REST no formato OpenAPI/Swagger.
- Facilita o entendimento e teste dos endpoints via interface web amigável.

### 6. Flyway Core e Flyway Database PostgreSQL
- Controla o versionamento e migração do esquema do banco de dados PostgreSQL.
- Automatiza atualizações e manutenção da estrutura do banco.

### 7. Driver JDBC PostgreSQL
- Driver necessário para conectar e executar comandos no banco PostgreSQL.

### 8. Banco de Dados H2
- Banco de dados em memória para testes e desenvolvimento local rápido.

### 9. ModelMapper
- Biblioteca para mapeamento automático entre objetos (DTOs e entidades).
- Reduz código manual e erros ao copiar dados entre classes.

### 10. Lombok
- Reduz boilerplate em classes Java, gerando getters, setters e construtores automaticamente.
- Facilita a escrita de código limpo e conciso.

---

## Resumo

Cada uma dessas dependências contribui para um aspecto fundamental da aplicação:

- Desenvolvimento rápido e simplificado (Spring Boot)
- Persistência e manipulação de dados (Spring Data JPA, ModelMapper)
- Validação de dados (Spring Validation)
- Documentação e testes da API (Springdoc OpenAPI)
- Monitoramento e manutenção (Spring Boot Actuator, Flyway)
- Suporte a bancos de dados (PostgreSQL, H2)
- Melhor produtividade e menos código repetitivo (Lombok)

---

Este conjunto forma uma base sólida para desenvolver APIs REST modernas, escaláveis e bem documentadas.

