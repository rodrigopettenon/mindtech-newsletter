import React, { useState } from 'react';
import '../styles/FormularioDescadastro.css';
import { apiNewsletter } from '../services/api';
import AlertModal from './AlertModal';

const FormularioDescadastro: React.FC = () => {
    const [email, setEmail] = useState('');
    const [modal, setModal] = useState({
        isOpen: false,
        type: 'success' as 'success' | 'error',
        title: '',
        message: ''
    });

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        
        // Validação básica do email
        if (!email || !email.includes('@')) {
            setModal({
                isOpen: true,
                type: 'error',
                title: 'Email inválido', // ✅ TÍTULO CORRETO
                message: 'Por favor, insira um email válido com @'
            });
            return;
        }
        
        try {
            console.log('Cancelando inscrição do email:', email);
            
            // 🚀 CHAMA A API DE CANCELAMENTO
            const resposta = await apiNewsletter.cancelarInscricao(email);
            console.log('Resposta do cancelamento:', resposta);
            
            setModal({
                isOpen: true,
                type: 'success',
                title: 'Inscrição cancelada!', // ✅ TÍTULO CORRETO (apenas para sucesso)
                message: resposta.message || 'Sua inscrição foi cancelada com sucesso.'
            });
            setEmail(''); // Limpa o campo
        } catch (error: any) {
            console.error('Erro no cancelamento:', error);
            setModal({
                isOpen: true,
                type: 'error',
                title: 'Email não encontrado', // ✅ TÍTULO CORRETO (para erros)
                message: error.message || 'Não foi possível cancelar a inscrição. Verifique se o email está correto.'
            });
        }
    };

    return (
        <div className="container-descadastro">
            <div className="card-preto descadastro-card">
                <div className="conteudo-descadastro">
                    <h1 className="titulo-descadastro">Cancelar Inscrição</h1>
                    
                    <p className="descricao-descadastro">
                        Para cancelar sua inscrição na newsletter, insira seu email abaixo.
                    </p>

                    <form onSubmit={handleSubmit} className="formulario-descadastro">
                        <div className="campo-email">
                            <label htmlFor="email-descadastro">E-mail</label>
                            <input
                                type="email"
                                id="email-descadastro"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                placeholder="email@email.com"
                                required
                            />
                        </div>

                        <div className="linha-divisoria"></div>

                        <button type="submit" className="botao-cancelar">
                            Cancelar Inscrição
                        </button>
                    </form>
                </div>
            </div>

            <AlertModal
                isOpen={modal.isOpen}
                type={modal.type}
                title={modal.title}
                message={modal.message}
                onClose={() => setModal({ ...modal, isOpen: false })}
            />
        </div>
    );
};

export default FormularioDescadastro;