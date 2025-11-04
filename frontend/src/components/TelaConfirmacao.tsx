import React from 'react';
import '../styles/TelaConfirmacao.css';
import LogoMindtech from '../assets/images/logo-mindtech.svg';
import IconeCheck from '../assets/images/icone.svg';

interface TelaConfirmacaoProps {
    onVoltar: () => void;
}

const TelaConfirmacao: React.FC<TelaConfirmacaoProps> = ({ onVoltar }) => {
    return (
        <div className="card-preto confirmacao-card">
            <div className="conteudo-confirmacao">
                <div className="icone-confirmacao">
                    <img src={IconeCheck} alt="Check" className="icone-check-confirmacao" />
                </div>

                <h1 className="titulo-confirmacao">
                    Obrigado por se inscrever na nossa newsletter!
                </h1>
                
                <p className="mensagem-confirmacao">
                    Agora você faz parte da comunidade Mindtech e está a um passo de ficar 
                    atualizado com as últimas inovações e tendências em Internet das Coisas (IoT).
                </p>

                <div className="rodape-confirmacao">
                    <img 
                        src={LogoMindtech} 
                        alt="Mindtech" 
                        className="logo-confirmacao"
                    />
                </div>

                <button 
                    className="botao-voltar"
                    onClick={onVoltar}
                >
                    Voltar
                </button>
            </div>
        </div>
    );
};

export default TelaConfirmacao;