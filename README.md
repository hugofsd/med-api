Voll.med API (Spring Boot 3)

API REST para gestão de médicos, pacientes e consultas (agendamentos e cancelamentos) da clínica Voll.med. O projeto segue uma arquitetura limpa em camadas (controller → domain/service → repository), usa validações de regras de negócio desacopladas e autenticação via JWT.

Visão Geral
- Cadastro, listagem, atualização e exclusão lógica de médicos e pacientes
- Agendamento e cancelamento de consultas com validações de regra de negócio
- Autenticação e autorização com Spring Security + JWT (Bearer Token)
- Persistência com Spring Data JPA (Hibernate) e migrações com Flyway
- Documentação interativa via Swagger UI (springdoc-openapi)

Arquitetura e Organização
- `controller`: recursos REST (Medico, Paciente, Consulta, Autenticação)
- `domain`: entidades, DTOs, repositórios e serviços
  - `medico`, `paciente`, `consulta`, `usuario`
  - `consulta/validacoes`: validadores de agendamento e cancelamento (pipeline)
- `infra`:
  - `security`: configuração do Spring Security, filtro JWT e emissão/validação do token
  - `exception`: tratamento centralizado de erros
  - `springdoc`: configuração do Swagger/OpenAPI

Stack Principal
- Java 17, Spring Boot 3.5.x
- Spring Web, Spring Data JPA, Bean Validation
- Spring Security + JWT (auth0 java-jwt)
- MySQL 8 + Flyway
- Lombok
- Swagger/OpenAPI (springdoc)

Banco de Dados e Migrações
- Tabelas: `medicos`, `pacientes`, `consultas`, `usuarios`
- Migrações em `src/main/resources/db/migration` (V1...Vn)
- Soft delete via campo `ativo` em médicos e pacientes

Configuração
Crie um banco local para dev e outro para testes (opcional):
- `vollmed`
- `vollmed_api_test`

Ajuste credenciais em `src/main/resources/application.properties` (ou use `application-mysql.properties` com variáveis de ambiente `DB_USER`/`DB_PASS`). Exemplo dev:
```
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
api.security.token.secret=uma_chave_secreta_bem_grande
```

Perfil de testes (`src/main/resources/application-test.properties`):
```
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api_test
```

Como Executar
- Build: `mvn clean package`
- Rodar: `mvn spring-boot:run`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

Autenticação (JWT)
1) POST `/login` com JSON:
```
{
  "login": "usuario",
  "senha": "senha"
}
```
2) Receba `token` (JWT). Envie nas próximas requisições:
```
Authorization: Bearer SEU_TOKEN
```
Os endpoints de domínio exigem Bearer (vide anotação `@SecurityRequirement(name = "bearer-key")`).

Endpoints Principais
- Médicos (`/medicos`)
  - POST cadastrar, GET paginado, PUT atualizar, DELETE lógico, GET por id
- Pacientes (`/paciente`)
  - POST cadastrar, GET paginado, PUT atualizar, DELETE lógico, GET por id
- Consultas (`/consultas`)
  - POST agendar, DELETE cancelar, GET listar

Paginação e ordenação seguem convenções Spring (`page`, `size`, `sort`) e foram traduzidas via propriedades (`pagina`, `tamanho`, `ordem`).

Regras de Negócio (exemplos)
- Paciente não pode ter outra consulta no mesmo dia/hora
- Médico não pode ter conflito de horário e deve estar ativo
- Horários respeitam funcionamento e antecedência mínima para agendar/cancelar

Testes
- Perfil `test` ativo (`@ActiveProfiles("test")`)
- Execute: `mvn test`

Erros e Tratamento
- Tratamento centralizado em `infra/exception/TratadorDeErros`
- Respostas padronizadas para validação (400), não encontrado (404) e conflitos (409)

Observações
- Para listar entidades com associações LAZY, prefira DTOs ou `fetch` controlado; evite expor entidades diretamente
- Flyway mantém o schema sincronizado entre ambientes

English (short)
Voll.med API is a Spring Boot 3 REST service to manage doctors, patients and appointments. It implements JWT auth, persistence with JPA + Flyway, business validations through a validator pipeline, and interactive docs via Swagger.
