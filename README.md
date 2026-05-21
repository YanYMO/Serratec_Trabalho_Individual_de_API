# 🎓 Praxis — API RESTful de Cursos Comunitários

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Maven](https://img.shields.io/badge/Maven-3.x-red)

## 👤 Dados do Aluno

| Campo | Informação |
|---|---|
| **Nome** | Yan Martins de Oliveira |
| **Tema** | Plataforma de Cursos Comunitários |
| **GitHub** | [YanYMO](https://github.com/YanYMO) |

---

## 📋 Descrição do Projeto

O **Praxis** é uma API RESTful desenvolvida com Java e Spring Boot para o gerenciamento de uma plataforma de cursos comunitários. O sistema permite o cadastro e gerenciamento de alunos, professores, cursos e matrículas, com foco em boas práticas de desenvolvimento backend, arquitetura em camadas e persistência de dados com PostgreSQL.

---

## 🛠 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA / Hibernate**
- **PostgreSQL 16**
- **Maven**
- **Swagger / OpenAPI 3**
- **Bean Validation (Jakarta)**
- **Git / GitHub**

---

## 📁 Estrutura de Pacotes

```
src/main/java/org/serratec/praxis/
├── config/          # Configuração do Swagger/OpenAPI
├── controller/      # Endpoints HTTP
├── service/         # Lógica de negócio
├── repository/      # Comunicação com o banco via JPA
├── domain/          # Entidades JPA
├── dto/
│   ├── request/     # DTOs de entrada (POST/PUT)
│   └── response/    # DTOs de saída (GET/POST/PUT)
├── enums/           # Enumerações do sistema
└── exception/       # Exceções customizadas e handler global
```

---

## ⚙️ Pré-requisitos

- Java 21+
- Maven 3.8+
- PostgreSQL 16+
- IntelliJ IDEA (recomendado)

---

## 🚀 Como Executar

### 1. Clonar o repositório
```bash
git clone https://github.com/YanYMO/Serratec_Trabalho_final_de_API.git
cd Serratec_Trabalho_final_de_API/praxis
```

### 2. Criar o banco de dados
```sql
CREATE DATABASE praxis;
```

### 3. Configurar o `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/praxis
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Executar a aplicação
```bash
mvn spring-boot:run
```

### 5. Acessar o Swagger
```
http://localhost:8080/swagger-ui.html
```

---

## 🔗 Relacionamentos JPA

| Tipo | Entidades |
|---|---|
| `@OneToOne` | Aluno ↔ PerfilSocial |
| `@OneToMany` | Aluno → Matriculas / Curso → Matriculas |
| `@ManyToOne` | Matricula → Aluno / Matricula → Curso |
| `@ManyToMany` | Curso ↔ Professor |

---

## 📌 Endpoints

### 👨‍🎓 Alunos — `/alunos`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/alunos` | Lista todos os alunos |
| `GET` | `/alunos/{id}` | Busca aluno por ID |
| `POST` | `/alunos` | Cadastra novo aluno |
| `PUT` | `/alunos/{id}` | Atualiza aluno |
| `DELETE` | `/alunos/{id}` | Remove aluno |

**Body POST/PUT:**
```json
{
  "nome": "Yan Martins de Oliveira",
  "cpf": "123.456.789-09",
  "dataNascimento": "11/05/1999",
  "email": "yan@email.com",
  "senha": "senha123segura",
  "perfilSocialRequestDTO": {
    "genero": "MASCULINO",
    "nivelEscolaridade": 4,
    "rendaFamiliar": 3
  }
}
```

---

### 🧑‍🏫 Professores — `/professores`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/professores` | Lista todos os professores |
| `GET` | `/professores/{id}` | Busca professor por ID |
| `POST` | `/professores` | Cadastra novo professor |
| `PUT` | `/professores/{id}` | Atualiza professor |
| `DELETE` | `/professores/{id}` | Remove professor |

**Body POST/PUT:**
```json
{
  "nome": "Carlos Silva",
  "cpf": "987.654.321-00",
  "dataNascimento": "10/03/1985",
  "email": "carlos@email.com",
  "senha": "senha123segura"
}
```

---

### 📚 Cursos — `/cursos`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/cursos` | Lista todos os cursos |
| `GET` | `/cursos/{id}` | Busca curso por ID |
| `POST` | `/cursos` | Cadastra novo curso |
| `POST` | `/cursos/curso/{cursoId}/professor/{professorId}` | Vincula professor ao curso |
| `PUT` | `/cursos/{id}` | Atualiza curso |
| `DELETE` | `/cursos/{id}` | Remove curso |

**Body POST/PUT:**
```json
{
  "nome": "Java Spring Boot",
  "descricao": "Curso completo de desenvolvimento backend com Spring Boot",
  "dataInicio": "20/05/2026",
  "duracaoEmHoras": 150,
  "tipo": "PRESENCIAL"
}
```

---

### 📝 Matrículas — `/matriculas`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/matriculas` | Lista todas as matrículas |
| `GET` | `/matriculas/{id}` | Busca matrícula por ID |
| `POST` | `/matriculas` | Cadastra nova matrícula |
| `PUT` | `/matriculas/{id}` | Atualiza status da matrícula |
| `DELETE` | `/matriculas/{id}` | Remove matrícula |

**Body POST:**
```json
{
  "codigo": 12345,
  "status": "ATIVA",
  "alunoId": 1,
  "cursoId": 2
}
```

**Body PUT:**
```json
{
  "status": "SUSPENSA"
}
```

---

### 👤 Perfis Sociais — `/perfis-sociais`

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/perfis-sociais` | Lista todos os perfis sociais |
| `PUT` | `/perfis-sociais/aluno/{idAluno}` | Atualiza perfil social do aluno |

**Body PUT:**
```json
{
  "genero": "FEMININO",
  "nivelEscolaridade": 6,
  "rendaFamiliar": 2
}
```

---

## 📊 Enums e Valores Aceitos

### Genero
| Valor | Descrição |
|---|---|
| `MASCULINO` | Masculino |
| `FEMININO` | Feminino |
| `OUTROS` | Outros |

### NivelEscolaridade
| Código | Descrição |
|---|---|
| `1` | Fundamental Incompleto |
| `2` | Fundamental Completo |
| `3` | Médio Incompleto |
| `4` | Médio Completo |
| `5` | Superior Incompleto |
| `6` | Superior Completo |

### RendaFamiliar
| Código | Descrição |
|---|---|
| `1` | Até R$ 1.580 |
| `2` | Entre R$ 1.581 e R$ 2.525 |
| `3` | Entre R$ 2.526 e R$ 10.885 |
| `4` | Entre R$ 10.886 e R$ 25.000 |
| `5` | Acima de R$ 25.000 |

### StatusMatricula
| Valor | Descrição |
|---|---|
| `ATIVA` | Matrícula ativa |
| `SUSPENSA` | Matrícula suspensa |
| `CANCELADA` | Matrícula cancelada |

### TipoCurso
| Valor | Descrição |
|---|---|
| `PRESENCIAL` | Curso presencial |
| `EAD` | Curso a distância |
| `SEMI_PRESENCIAL` | Curso híbrido |

---

## ⚠️ Respostas de Erro

| Status | Situação |
|---|---|
| `400` | Dados inválidos ou formato incorreto |
| `404` | Recurso não encontrado |
| `409` | CPF ou e-mail já cadastrado no sistema |

**Exemplo de resposta de erro:**
```json
{
  "status": 409,
  "titulo": "CPF já cadastrado",
  "dataHora": "2026-05-22T10:30:00",
  "erros": []
}
```

---

## 📄 Licença

Distribuído sob a [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).