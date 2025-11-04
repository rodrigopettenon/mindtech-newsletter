
---

# 📋 **BACK-END NEWSLETTER MINDTECH - GUIA DE INSTALAÇÃO**

## 🚀 **PRÉ-REQUISITOS OBRIGATÓRIOS**

### **1. Java 17**
```bash
# Verificar se já tem instalado
java -version
```
**Download:** [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou [OpenJDK 17](https://openjdk.org/projects/jdk/17/)

### **2. PostgreSQL** ⚠️ **OBRIGATÓRIO**
```bash
# Verificar se já tem instalado
psql --version
```
**Download:** [PostgreSQL Download](https://www.postgresql.org/download/)

### **3. Maven** (já incluído no projeto - `mvnw`)
```bash
# Verificar se já tem instalado (opcional)
mvn -version
```

---

## 🗄️ **CONFIGURAÇÃO DO BANCO DE DADOS**

### **Passo 1: Instalar PostgreSQL**
- Baixe e instale o PostgreSQL em sua máquina
- **Anote a senha do usuário `postgres`** durante a instalação

### **Passo 2: Criar Banco de Dados**
```sql
-- Conectar ao PostgreSQL (via pgAdmin ou linha de comando)
CREATE DATABASE newsletter;
```

### **Passo 3: Verificar Conexão**
```bash
# Testar conexão (Linux/Mac/Windows)
psql -h localhost -p 5432 -U postgres -d newsletter
```

---

## 🔧 **CONFIGURAÇÃO DO PROJETO**

### **1. Navegar para a pasta do back-end**
```bash
cd newsletter-backend
```

### **2. Configurar credenciais do banco** ⚠️ **IMPORTANTE**
Edite o arquivo `src/main/resources/application.properties`:

```properties
# ⚠️ ALTERAR A SENHA ABAIXO PARA A SUA SENHA DO POSTGRES!
spring.datasource.password=SUA_SENHA_DO_POSTGRES_AQUI
```

**Exemplo:**
```properties
spring.datasource.password=minha_senha_123
```

### **3. Verificar estrutura do projeto**
```
newsletter-backend/
├── src/
│   └── main/
│       ├── java/com/mindtech/newsletter/
│       │   ├── config/     # Configuração CORS
│       │   ├── controller/ # Endpoints da API
│       │   ├── service/    # Lógica de negócio
│       │   ├── repository/ # Acesso ao banco
│       │   └── model/      # Entidade JPA
│       └── resources/
│           └── application.properties
├── pom.xml
└── mvnw
```

---

## 🏃 **EXECUTAR O BACK-END**

### **Método 1: Usando Maven Wrapper (RECOMENDADO)**
```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### **Método 2: Usando Maven instalado**
```bash
mvn spring-boot:run
```

### **Método 3: Gerar JAR e executar**
```bash
# Gerar arquivo JAR
./mvnw clean package

# Executar JAR
java -jar target/newsletter-0.0.1-SNAPSHOT.jar
```

---

## ✅ **VERIFICAR SE ESTÁ RODANDO**

### **1. Console deve mostrar:**
```
Tomcat started on port 8080
Started MindtechNewsletterBackendApplication
```

### **2. Testar endpoints:**
```bash
# Testar se a API responde
curl http://localhost:8080/mindtech-newsletter/inscricao
```

### **3. Acessar logs:**
Verifique os logs no console para confirmar:
- ✅ Conexão com PostgreSQL estabelecida
- ✅ Tabelas criadas/atualizadas
- ✅ Aplicação rodando na porta 8080

---

## 🛠️ **SOLUÇÃO DE PROBLEMAS**

### **Problema: Erro de conexão com PostgreSQL**
```bash
# Verificar se PostgreSQL está rodando
sudo systemctl status postgresql  # Linux
# ou verificar serviço PostgreSQL no Windows
```

### **Problema: "Password authentication failed"**
- Verificar `application.properties` - senha correta?
- Testar conexão manual: `psql -U postgres`

### **Problema: Porta 8080 em uso**
```bash
# Encontrar processo usando a porta
netstat -ano | findstr :8080  # Windows
lsof -i :8080  # Linux/Mac
```

### **Problema: Dependências Maven**
```bash
# Limpar e reinstalar
./mvnw clean install
```

### **Problema: Java não encontrado**
- Verificar `JAVA_HOME` environment variable
- Verificar se Java 17 está no PATH

---

## 📡 **ENDPOINTS DA API**

### **Inscrição**
```http
POST http://localhost:8080/mindtech-newsletter/inscricao
Content-Type: application/json

{
  "email": "usuario@exemplo.com"
}
```

### **Cancelamento**
```http
DELETE http://localhost:8080/mindtech-newsletter/cancelar-inscricao?email=usuario@exemplo.com
```

---

## 🧪 **EXECUTAR TESTES**

```bash
# Executar todos os testes
./mvnw test

# Executar testes com relatório
./mvnw surefire-report:report
```

---

## 🔒 **CONFIGURAÇÕES TÉCNICAS**

- **Porta:** 8080
- **Banco:** PostgreSQL
- **ORM:** JPA/Hibernate
- **CORS:** Configurado para front-end React
- **Validações:** Email único, formato válido, tamanho máximo

---

## ✅ **CHECKLIST DE VERIFICAÇÃO**

- [ ] PostgreSQL instalado e rodando
- [ ] Banco `newsletter` criado
- [ ] Senha atualizada no `application.properties`
- [ ] Aplicação inicia sem erros
- [ ] Logs mostram conexão bem-sucedida com banco
- [ ] API responde na porta 8080

---

**Próximo passo:** Configurar o front-end após back-end estar rodando! 🚀

---
