# 💰 Finance API

API REST para gerenciamento financeiro pessoal, desenvolvida com Java e Spring Boot.
Permite que usuários cadastrem receitas e despesas, organizem por categorias e filtrem por período.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.5**
- **Spring Security + JWT** — autenticação stateless
- **Spring Data JPA + Hibernate** — persistência de dados
- **PostgreSQL** — banco de dados relacional
- **Docker + Docker Compose** — containerização
- **Swagger / OpenAPI 3** — documentação interativa

## ⚙️ Como rodar localmente

### Pré-requisitos
- Java 21+
- Docker Desktop

### Passos

```bash
# Clone o repositório
git clone https://github.com/BrunoMoraix/finance-api.git
cd finance-api

# Suba o banco de dados
docker compose up -d

# Rode a aplicação
./mvnw spring-boot:run
```

Acesse a documentação em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 📡 Endpoints

### Autenticação
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/api/auth/register` | Cadastrar novo usuário |
| POST | `/api/auth/login` | Login e geração do token JWT |

### Categorias 🔒
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/categories` | Listar categorias |
| POST | `/api/categories` | Criar categoria |
| DELETE | `/api/categories/{id}` | Remover categoria |

### Transações 🔒
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/api/transactions` | Criar transação |
| GET | `/api/transactions/user/{userId}` | Listar por usuário |
| GET | `/api/transactions/user/{userId}/period` | Filtrar por período |
| DELETE | `/api/transactions/{id}` | Remover transação |

> 🔒 Endpoints protegidos exigem token JWT no header: `Authorization: Bearer <token>`

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação stateless.

**Fluxo:**
1. Cadastre um usuário em `POST /api/auth/register`
2. Faça login em `POST /api/auth/login` e copie o token
3. Clique em **Authorize** no Swagger e cole o token
4. Agora você pode acessar todos os endpoints protegidos

## 📦 Exemplo de uso

**Registrar usuário:**
```json
POST /api/auth/register
{
  "name": "Bruno Morais",
  "email": "bruno@email.com",
  "password": "123456"
}
```

**Login:**
```json
POST /api/auth/login
{
  "email": "bruno@email.com",
  "password": "123456"
}
```

**Criar transação:**
```json
POST /api/transactions
{
  "userId": 1,
  "categoryId": 1,
  "description": "Mercado",
  "amount": 250.00,
  "date": "2026-04-17",
  "type": "EXPENSE"
}
```

## 🗂️ Estrutura do projeto

```
src/main/java/com/moraix/finance_api/
├── config/       # JWT, Security, Swagger
├── controller/   # Endpoints REST
├── service/      # Regras de negócio
├── repository/   # Acesso ao banco
├── model/        # Entidades JPA
├── dto/          # Objetos de transferência
└── exception/    # Tratamento global de erros
```

## 📄 Licença

Este projeto está sob a licença MIT.
