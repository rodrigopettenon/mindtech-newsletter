
```markdown
# 🎨 Front-end Newsletter Mindtech

Aplicação React/TypeScript para sistema de inscrição em newsletter.

## 🚀 Tecnologias e Versões
- **React 19.2.0** ⚡ (versão mais recente)
- **TypeScript 4.9.5**
- **React DOM 19.2.0**
- **React Scripts 5.0.1**
- **CSS Modules** para estilização
- **Fetch API** para comunicação com back-end

## 📁 Estrutura do Projeto
```
frontend/
├── public/                 # Arquivos públicos estáticos
├── src/                   # Código fonte principal
│   ├── assets/           # Imagens, fontes e recursos visuais
│   ├── components/       # Componentes React reutilizáveis
│   ├── services/         # Integração com API back-end
│   ├── styles/           # Arquivos CSS modularizados
│   ├── types/            # Definições TypeScript
│   └── App.tsx           # Componente raiz da aplicação
├── build/                # Build de produção (gerado)
├── package.json          # Dependências e scripts
└── tsconfig.json         # Configuração TypeScript
```

## 🛠️ Como Executar

### 1. Instalar Dependências
```bash
cd frontend
npm install
```

### 2. Executar em Modo Desenvolvimento
```bash
npm start
```
A aplicação estará disponível em: **http://localhost:3000**

### 3. Outros Scripts Disponíveis
```bash
npm test          # Executar testes
npm run build     # Gerar build de produção
```

## ⚠️ Pré-requisitos
- **Back-end Spring Boot** rodando em `http://localhost:8080`
- **Node.js 16+** (compatível com React 19)
- **npm** ou **yarn** para gerenciamento de dependências

## 🎯 Funcionalidades Implementadas

### Inscrição
- ✅ Formulário de inscrição com validação de email
- ✅ Integração com endpoint `POST /mindtech-newsletter/inscricao`
- ✅ Tela de confirmação após inscrição bem-sucedida
- ✅ Tratamento de emails duplicados

### Cancelamento
- ✅ Formulário de cancelamento com validação
- ✅ Integração com endpoint `DELETE /mindtech-newsletter/cancelar-inscricao`
- ✅ Feedback visual para todos os cenários

### Experiência do Usuário
- ✅ Design fiel às especificações da Mindtech
- ✅ Navegação entre telas intuitiva
- ✅ Modal de alerta para feedback
- ✅ Validações em tempo real
- ✅ Interface responsiva

## 🔧 Desenvolvimento

### Estrutura de Componentes
- `FormularioInscricao` - Formulário principal de inscrição
- `FormularioDescadastro` - Formulário de cancelamento
- `TelaConfirmacao` - Tela de sucesso após inscrição
- `AlertModal` - Modal para feedback visual
- `App` - Componente principal com navegação

### Integração com API
```typescript
// services/api.ts
export const apiNewsletter = {
  async inscrever(email: string): Promise<RespostaInscricao>,
  async cancelarInscricao(email: string): Promise<RespostaInscricao>
}
```

## 🐛 Solução de Problemas

### Erro de Dependências
```bash
# Limpar cache e reinstalar
rm -rf node_modules
npm install
```

### Erro de Conexão com Back-end
- Verificar se back-end está rodando na porta 8080
- Verificar console do navegador para detalhes do erro

### Porta 3000 Ocupada
- O React automaticamente oferece usar outra porta (3001, 3002, etc.)

### Build de Produção
```bash
npm run build
# Os arquivos otimizados ficarão na pasta /build
```

---

**Desenvolvido com React 19 e TypeScript para máxima produtividade e segurança de tipos** ✨
```

## ✅ **AGORA COMMITAMOS TUDO:**

```bash
cd "C:\Users\Windows11\Desktop\Newsletter Mindtech\projeto-completo"
git add .
git commit -m "feat: Documentação completa e profissional

- README específico do back-end com instruções PostgreSQL
- README específico do front-end com tecnologias exatas
- README principal com visão geral do projeto
- Estrutura organizada para fácil execução"
git push origin main
```