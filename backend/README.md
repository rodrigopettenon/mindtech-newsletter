---

# 📰 Mindtech Newsletter — Backend

API REST desenvolvida para o sistema de inscrição na newsletter da **Mindtech**.
O objetivo é permitir que usuários se inscrevam e cancelem suas inscrições por meio de endpoints simples e integráveis com o front-end.

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.4.11**
* **Maven**
* **PostgreSQL**
* **JPA (EntityManager + Native Queries)**
* **Apache Commons Lang 3**
* **Spring Validation**
* **Spring Web**

---

## ⚙️ Pré-requisitos de Instalação

> As instruções abaixo partem do zero, para alguém que **ainda não possui nenhum ambiente configurado**.

### 🧩 1. Instalar o Java JDK 17

Baixe e instale o JDK 17 (LTS):

* [Download JDK 17 (Oracle)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  ou
* [Download JDK 17 (OpenJDK)](https://adoptium.net/temurin/releases/)

Após instalar, confirme no terminal:

```bash
java -version
```

Saída esperada:

```
openjdk version "17.0.x"
```

---

### 🧰 2. Instalar o Maven

Baixe e instale o Maven:
🔗 [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

Verifique a instalação:

```bash
mvn -version
```

---

### 🗄️ 3. Instalar o PostgreSQL

Baixe e instale o **PostgreSQL**:

* [https://www.postgresql.org/download/](https://www.postgresql.org/download/)

Após a instalação:

1. Crie um banco chamado `newsletter`
2. Defina o usuário e senha padrão:

   * **Usuário:** `postgres`
   * **Senha:** `root`

Caso use outras credenciais, ajuste no arquivo:

```
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/newsletter
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

---

### 💡 4. Instalar uma IDE (opcional)

Você pode utilizar:

* [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/)
* [Spring Tools Suite (STS)](https://spring.io/tools)

Ou simplesmente rodar o projeto direto pelo terminal.

---

## 🏗️ Executando o Projeto

### 🔹 Clonar o repositório

```bash
git clone https://github.com/seu-usuario/mindtech-newsletter-backend.git
cd mindtech-newsletter-backend
```

### 🔹 Compilar o projeto

```bash
mvn clean install
```

### 🔹 Executar a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em:
👉 **[http://localhost:8080](http://localhost:8080)**

---

## 📬 Endpoints Principais

|  Método  | Endpoint                                                | Descrição                       |
| :------: | :------------------------------------------------------ | :------------------------------ |
|  `POST`  | `/mindtech-newsletter/inscricao`                        | Inscreve um email na newsletter |
| `DELETE` | `/mindtech-newsletter/cancelar-inscricao?email={email}` | Cancela uma inscrição existente |

### 🔸 Exemplo de `POST /mindtech-newsletter/inscricao`

**Request body:**

```json
{
  "email": "usuario@exemplo.com"
}
```

**Response (201):**

```json
{
  "timestamp": "2025-11-04T18:30:15Z",
  "status": 201,
  "object": {
    "email": "usuario@exemplo.com",
    "dataHoraCriacao": "04/11/2025 15:30:15"
  }
}
```

---

## 🧱 Estrutura do Projeto

```
mindtech-newsletter-backend/
├── src/main/java/com/mindtech/newsletter/
│   ├── controller/
│   │   └── InscricaoController.java
│   ├── service/
│   │   └── InscricaoService.java
│   ├── repository/
│   │   └── InscricaoRepository.java
│   ├── model/
│   │   └── InscricaoModel.java
│   ├── dto/
│   │   └── InscricaoDto.java
│   ├── config/
│   │   └── CorsConfig.java
│   ├── exception/
│   │   └── MindTechErrorException.java
│   ├── util/
│   │   ├── LogUtil.java
│   │   └── EmailUtils.java
│   └── MindtechNewsletterApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

---

## 🧩 Boas Práticas Implementadas

✅ Validação de email (formato e tamanho)
✅ Respostas padronizadas (`StandardObjectReturn`)
✅ Logs de processo, validação, sucesso e erro (`LogUtil`)
✅ CORS configurado para integração com front-end local
✅ Camadas bem definidas (`Controller`, `Service`, `Repository`)
✅ Queries nativas com `EntityManager`
✅ Tratamento de exceções personalizadas

---

## 🤝 Integração com o Front-end

O back-end está preparado para aceitar requisições CORS dos seguintes endereços:

* `http://localhost:3000`
* `http://localhost:5173`

Isso permite integração direta com projetos criados em **React**, **Vite** ou outras ferramentas.

---

## 🧠 Autor

**Rodrigo Pettenon**
Desenvolvedor Back-end Java
📧 [[rodrigopettenon.dev@gmail.com](mailto:seu-email@email.com)]
💼 [linkedin.com/in/rodrigopettenon](https://linkedin.com/in/rodrigopettenon)

---
