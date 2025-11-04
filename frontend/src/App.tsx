import React, { useState } from 'react';
import FormularioInscricao from './components/FormularioInscricao';
import FormularioDescadastro from './components/FormularioDescadastro';
import './App.css';

type Tela = 'inscricao' | 'descadastro';

function App() {
  const [telaAtual, setTelaAtual] = useState<Tela>('inscricao');

  return (
    <div className="App">
      {/* Header de navegação simples */}
      <header className="header-app">
        <nav className="navegacao">
          <button 
            className={`nav-botao ${telaAtual === 'inscricao' ? 'nav-ativo' : ''}`}
            onClick={() => setTelaAtual('inscricao')}
          >
            Inscrever-se
          </button>
          <button 
            className={`nav-botao ${telaAtual === 'descadastro' ? 'nav-ativo' : ''}`}
            onClick={() => setTelaAtual('descadastro')}
          >
            Cancelar Inscrição
          </button>
        </nav>
      </header>

      {/* Conteúdo das telas */}
      <main>
        {telaAtual === 'inscricao' && <FormularioInscricao />}
        {telaAtual === 'descadastro' && <FormularioDescadastro />}
      </main>
    </div>
  );
}

export default App;