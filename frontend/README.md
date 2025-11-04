
---

````markdown
# 📰 Mindtech Newsletter - Frontend

Interface web desenvolvida em **React + TypeScript** para o sistema de inscrição e descadastro de newsletter da **Mindtech**.  
Este projeto consome as APIs do back-end e oferece uma interface moderna, responsiva e fiel ao design proposto.

---

## 🚀 Tecnologias Utilizadas

- **React 19**
- **TypeScript**
- **CSS Modules**
- **React Testing Library**
- **Node.js / npm**

---

## 🧰 Pré-requisitos

Antes de iniciar o projeto, verifique se você tem os seguintes itens instalados:

- [Node.js (versão LTS 18 ou superior)](https://nodejs.org/en/download)
- [npm](https://www.npmjs.com/get-npm) (instalado automaticamente com o Node.js)
- Back-end da aplicação rodando localmente (porta padrão: `8080`)

---

## ⚙️ Instalação e Execução

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/seu-usuario/newsletter-frontend.git
````

### 2️⃣ Acessar o diretório

```bash
cd newsletter-frontend
```

### 3️⃣ Instalar as dependências

```bash
npm install
```

### 4️⃣ Executar o projeto

```bash
npm start
```

A aplicação será iniciada automaticamente em:
👉 **[http://localhost:3000](http://localhost:3000)**

---

## 🔄 Integração com o Back-end

Por padrão, o front-end se comunica com a API do back-end através do endpoint:

```
http://localhost:8080/mindtech-newsletter
```

Caso o back-end esteja rodando em outra porta ou domínio, altere o valor no arquivo:

```
src/services/api.ts
```

```typescript
const BASE_URL = 'http://localhost:8080';
```

---

## 🧪 Executar Testes

Para rodar os testes automatizados:

```bash
npm test
```

Isso executará os testes configurados com **React Testing Library**.

---

## 🏗️ Gerar Build de Produção

Para gerar uma versão otimizada para deploy:

```bash
npm run build
```

Os arquivos finais serão gerados no diretório `/build`.

---

## 📁 Estrutura do Projeto

```
frontend/
├── build/
├── node_modules/
├── public/
├── src/
│   ├── assets/
│   ├── components/
│   ├── services/
│   ├── styles/
│   ├── types/
│   ├── App.css
│   ├── App.test.tsx
│   ├── App.tsx
│   ├── index.css
│   ├── index.tsx
│   ├── logo.svg
│   ├── react-app-env.d.ts
│   ├── reportWebVitals.ts
│   └── setupTests.ts
├── .gitignore
├── package.json
├── package-lock.json
├── README.md
└── tsconfig.json
```

---

## 🧑‍💻 Autor

Desenvolvido por **Rodrigo Pettenon**
🔗 [LinkedIn](https://www.linkedin.com/in/rodrigopettenon)
📧 [Contato profissional](mailto:rodrigopettenon.dev@gmail.com)

---

## 🏁 Observação Final

> Este projeto foi desenvolvido como parte do teste técnico da **Mindtech**, atendendo aos requisitos de funcionalidade, integração e clareza de documentação.

---
