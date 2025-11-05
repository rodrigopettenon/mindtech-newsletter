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

## 🧰 2. Instalar o Maven

O Maven é o gerenciador de dependências utilizado neste projeto.
Caso ainda não o tenha instalado, siga as instruções abaixo.

### 🔹 Opção 1 — Instalação Manual do Maven

1. Baixe a versão mais recente em:
   🔗 [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

2. Extraia o conteúdo em um diretório de sua preferência, por exemplo:

   ```
   C:\Arquivos de Programas\apache-maven-3.9.11
   ```

3. Configure as variáveis de ambiente:

   * **MAVEN_HOME** → caminho da pasta onde o Maven foi extraído
     Exemplo:

     ```
     MAVEN_HOME = C:\Arquivos de Programas\apache-maven-3.9.11
     ```

   * **Path** → acrescente o seguinte ao final da variável existente:

     ```
     ;%MAVEN_HOME%\bin
     ```

   > 💡 Para editar as variáveis de ambiente:
   >
   > * Abra o menu Iniciar e procure por **“Editar variáveis de ambiente do sistema”**
   > * Clique em **“Variáveis de ambiente”**
   > * Localize **Path** em “Variáveis do sistema” e clique em **Editar**

4. Verifique se o Maven foi instalado corretamente:

   Abra o **Prompt de Comando** e execute:

   ```
   mvn -version
   ```

   O resultado esperado deve ser semelhante a:

   ```
   Apache Maven 3.9.11 (3e54c93a704957b63ee3494413a2b544fd3d825b)
   Maven home: C:\Arquivos de Programas\apache-maven-3.9.11
   Java version: 17.0.12, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-17
   Default locale: pt_BR, platform encoding: Cp1252
   OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
   ```

   Se esse retorno aparecer, o Maven está configurado corretamente ✅

---

### 🔹 Opção 2 — Utilizar uma IDE com Maven integrado (recomendado)

Caso prefira evitar a configuração manual, é possível usar uma IDE que **já vem com Maven embutido**, como o **IntelliJ IDEA** ou o **Eclipse**.
Essas IDEs reconhecem automaticamente o arquivo `pom.xml` e executam os comandos Maven internamente, sem necessidade de ajustar variáveis de ambiente.

> Abaixo há uma seção dedicada explicando como utilizar o IntelliJ IDEA caso a instalação manual não funcione.

---

---

## 🧭 Executando o Projeto com IntelliJ IDEA (alternativa ao Maven manual)

Caso prefira **não configurar o Maven manualmente**, é possível rodar o backend diretamente pelo **IntelliJ IDEA**, que já inclui uma versão embutida do Maven.

---

### ⚙️ 1️⃣ Pré-requisitos

Antes de abrir o projeto, garanta que você tenha instalado:

* **IntelliJ IDEA** (Community ou Ultimate)
  🔗 [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* **JDK 17** configurado corretamente no sistema
  (O IntelliJ detecta automaticamente ou permite adicionar manualmente.)

---

### 🚀 2️⃣ Abrir o Projeto

1. Abra o IntelliJ e selecione **File → Open...**
2. Escolha a **pasta raiz do projeto backend**, onde está o arquivo `pom.xml`.
3. O IntelliJ detectará o projeto Maven e perguntará se deseja importar.

   * Marque **"Import Maven projects automatically"** (ou **"Enable auto-import"**).
4. Em seguida, configure o **JDK 17**:

   * Vá em `File → Project Structure → Project SDK`
   * Caso não exista, clique em **Add SDK → JDK** e selecione o diretório do Java 17
     (exemplo: `C:\Program Files\Java\jdk-17`)

---

### 🧩 3️⃣ Selecionar o Maven

O IntelliJ já traz um **Maven embutido**, então você pode:

* Usar o **Bundled Maven (recomendado)**
* Ou, se preferir, apontar para o Maven instalado manualmente em:
  `File → Settings → Build, Execution, Deployment → Build Tools → Maven → Maven home directory`

---

### ▶️ 4️⃣ Executar o Backend

1. No painel de arquivos, abra a classe principal:

   ```
   src/main/java/com/mindtech/newsletter/MindtechNewsletterApplication.java
   ```

2. Clique com o botão direito e escolha **Run 'MindtechNewsletterApplication'**.

3. O IntelliJ criará automaticamente a configuração e iniciará o servidor.

4. Aguarde até ver no console a mensagem:

   ```
   Started MindtechNewsletterApplication
   Tomcat started on port(s): 8080
   ```

5. O backend estará disponível em:
   👉 **[http://localhost:8080](http://localhost:8080)**

---

### 🧪 5️⃣ Executar Testes

Para rodar os testes automatizados:

* Clique com o botão direito sobre a pasta `src/test/java` → **Run 'All Tests'**
  ou
* Execute no terminal do IntelliJ:

  ```bash
  mvn test
  ```

---

### 🧰 6️⃣ Comandos Maven no IntelliJ

1. Abra o painel lateral **Maven** (ícone do elefante).

2. Expanda `Lifecycle` e clique duas vezes nos comandos:

   * `clean` → limpa o projeto
   * `install` → compila e instala as dependências
   * `package` → empacota a aplicação

3. Também é possível rodar diretamente o Spring Boot:

   ```
   Plugins → spring-boot → spring-boot:run
   ```

---

### 🧯 7️⃣ Solução de Problemas Comuns

| Problema                           | Solução                                                             |
| ---------------------------------- | ------------------------------------------------------------------- |
| **Erro de JDK**                    | Vá em `File → Project Structure → Project SDK` e selecione o JDK 17 |
| **Dependências não reconhecidas**  | Clique em `Reimport` no painel Maven                                |
| **Erro no Maven**                  | Use o Maven embutido do IntelliJ                                    |
| **Build falhando por cache Maven** | Execute `mvn -U clean install` no terminal do IntelliJ              |

---

### 🗄️ Instalar o PostgreSQL

Você pode instalar o PostgreSQL de duas formas: **manualmente** ou **usando o pgAdmin 4**, que já vem com o PostgreSQL incluso e oferece uma interface gráfica simples para gerenciar o banco.

---

#### 🧰 **Opção 1 — Instalação manual do PostgreSQL**

Baixe e instale o **PostgreSQL** diretamente pelo site oficial:

👉 [https://www.postgresql.org/download/](https://www.postgresql.org/download/)

Após a instalação:

1. Crie um banco de dados chamado:

   ```
   newsletter
   ```

2. Defina o usuário e senha padrão:

   * **Usuário:** `postgres`
   * **Senha:** `root`

3. Caso utilize outras credenciais, ajuste no arquivo:

   ```
   src/main/resources/application.properties
   ```

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/newsletter
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA
   ```

---

#### 💻 **Opção 2 — Instalação via pgAdmin 4 (recomendada para iniciantes)**

Se preferir uma interface gráfica para gerenciar o banco de dados:

1. Baixe o **pgAdmin 4**, que já inclui o PostgreSQL:
   👉 [https://www.pgadmin.org/download/](https://www.pgadmin.org/download/)

2. Durante a instalação, **anote a senha configurada para o usuário `postgres`**.

3. Após concluir a instalação, abra o **pgAdmin 4**, conecte-se ao servidor local e crie um banco chamado:

   ```
   newsletter
   ```

4. Verifique se as credenciais estão corretas no arquivo:

   ```properties
   spring.datasource.username=postgres
   spring.datasource.password=root
   ```

---

### 💡 4. Instalar uma IDE (opcional)

Você pode utilizar:

* [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/)
* [Spring Tools Suite (STS)](https://spring.io/tools)

Ou simplesmente rodar o projeto direto pelo terminal.

---

---

## 🧰 1. Instalar o Git

Para clonar o projeto localmente, é necessário ter o **Git** instalado na máquina.

### 🔹 Passos para instalação (Windows)

1. Acesse o site oficial:
   🔗 [https://git-scm.com/downloads](https://git-scm.com/downloads)
2. Baixe o instalador compatível com o seu sistema (ex: Windows 64-bit).
3. Durante a instalação, mantenha as opções padrão — especialmente:
   ✅ **“Add Git to PATH”** (necessário para usar o `git` no terminal).
4. Finalize a instalação.
5. Para confirmar se o Git foi instalado corretamente, execute no Prompt de Comando:

   ```bash
   git --version
   ```

   O resultado deve ser semelhante a:

   ```
   git version 2.47.0.windows.1
   ```

---

## 📦 2. Clonar o projeto

Após instalar o Git, execute o comando abaixo para clonar o repositório completo (contendo o **Front-end** e o **Back-end**):

```bash
git clone https://github.com/rodrigopettenon/mindtech-newsletter.git
```

Em seguida, entre na pasta do projeto:

```bash
cd mindtech-newsletter/backend
```

> O diretório `backend` contém a API desenvolvida em Spring Boot.
> O diretório `frontend` contém a interface da aplicação.

---

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
📧 [rodrigopettenon.dev@gmail.com](mailto:rodrigopettenon.dev@gmail.com)
💼 [linkedin.com/in/rodrigopettenon](https://linkedin.com/in/rodrigopettenon)

---
