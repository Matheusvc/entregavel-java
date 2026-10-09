# API REST de Alunos — Spring Boot + JPA + PostgreSQL

Entregável da Semana 02 — Desenvolvimento de APIs e Backend.
Autor: **Matheus Vitor Carneiro Lira** — RA **194035**.

API REST com arquitetura em camadas e persistência real no PostgreSQL.

## Arquitetura em camadas

```
Cliente (Postman/Insomnia)
        │  HTTP
        ▼
Controller  ──►  Service  ──►  Repository  ──►  PostgreSQL
(REST)          (regras)      (JpaRepository)   (Hibernate/JPA)
```

| Camada       | Classe                | Pacote                        |
|--------------|-----------------------|-------------------------------|
| Entity       | `Aluno`               | `br.com.techx.aula2.model`    |
| Repository   | `AlunoRepository`     | `br.com.techx.aula2.repository` |
| Service      | `AlunoService`        | `br.com.techx.aula2.service`  |
| Controller   | `AlunoController`     | `br.com.techx.aula2.controller` |
| Main         | `Aula2Application`    | `br.com.techx.aula2`          |

## Pré-requisitos (IMPORTANTE)

Esta máquina tem apenas **JRE 8 (32 bits)**, que **não compila nem roda** Spring Boot 3.
Antes de rodar, instale:

1. **JDK 17 (ou 21)** — ex.: [Eclipse Temurin 17](https://adoptium.net/temurin/releases/?version=17).
   Após instalar, confirme no terminal:
   ```bash
   java -version
   javac -version
   ```
   Ambos devem mostrar versão 17 (ou superior).
2. **PostgreSQL** instalado e rodando na porta `5432`.

## Configuração do banco

1. Crie o banco (via pgAdmin ou terminal `psql`):
   ```sql
   CREATE DATABASE escola_db;
   ```
2. Ajuste usuário/senha em `src/main/resources/application.properties`
   (por padrão: usuário `postgres`, senha `postgres`).

As tabelas **não** precisam ser criadas na mão: com `spring.jpa.hibernate.ddl-auto=update`,
o Hibernate cria a tabela `aluno` automaticamente ao iniciar a aplicação.

## Como rodar

Na pasta do projeto:

```bash
./mvnw spring-boot:run
```

No Windows (PowerShell/CMD):

```bash
mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.
Com `spring.jpa.show-sql=true`, você vê no console os comandos SQL do Hibernate.

## Endpoints

Base: `http://localhost:8080/alunos`

| Método | Rota                    | Descrição                          | Anotações |
|--------|-------------------------|------------------------------------|-----------|
| GET    | `/alunos`               | Lista todos os alunos              | `@GetMapping` |
| GET    | `/alunos/{id}`          | Busca aluno por id                 | `@PathVariable` |
| GET    | `/alunos/buscar?nome=`  | Busca alunos por nome              | `@RequestParam` |
| POST   | `/alunos`               | Cria um aluno                      | `@PostMapping`, `@RequestBody` |
| PUT    | `/alunos/{id}`          | Atualiza um aluno                  | `@PutMapping`, `@RequestBody` |
| DELETE | `/alunos/{id}`          | Remove um aluno                    | `@DeleteMapping` |
| GET    | `/alunos/ra`            | **Desafio:** frase com nome + RA   | `@GetMapping` |

### Desafio prático

```
GET http://localhost:8080/alunos/ra
```
Resposta:
```
O RA do aluno MATHEUS VITOR CARNEIRO LIRA é 194035
```

### Exemplo de criação (POST)

`POST http://localhost:8080/alunos` — body JSON:
```json
{
  "nome": "Matheus Vitor",
  "ra": 194035,
  "idade": 20,
  "curso": "Sistemas de Informação"
}
```

### Dica de teste

Use o **Postman** ou **Insomnia** para os endpoints de criação (POST) e listagem (GET).

> Observação: `mvnw spring-boot:run` **não** executa os testes.
> Se rodar `mvnw package`, o teste `contextLoads` tenta subir o contexto e precisa do
> PostgreSQL ligado. Para empacotar sem testes: `mvnw package -DskipTests`.
