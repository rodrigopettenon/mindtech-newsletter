import { apiNewsletter } from '../services/api';
import React, { useState } from 'react';
import '../styles/FormularioInscricao.css';
import ImagemMindtech from '../assets/images/Imagem.png';
import LogoMindtech from '../assets/images/logo-mindtech.svg';
import IconeCheck from '../assets/images/icone.svg';
import AlertModal from './AlertModal';
import TelaConfirmacao from './TelaConfirmacao';

const FormularioInscricao: React.FC = () => {
    const [email, setEmail] = useState('');
    const [telaAtual, setTelaAtual] = useState<'formulario' | 'confirmacao'>('formulario');

    const [modal, setModal] = useState({
        isOpen: false,
        type: 'success' as 'success' | 'error',
        title: '',
        message: ''
    });

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        
        if (!email || !email.includes('@')) {
            setModal({
                isOpen: true,
                type: 'error',
                title: 'Email inválido',
                message: 'Por favor, insira um email válido com @'
            });
            return;
        }
        
        try {
            console.log('Enviando email para API:', email);
            
            const resposta = await apiNewsletter.inscrever(email);
            console.log('Resposta completa da API:', resposta);
            
            if (resposta.status === 201) {
                setTelaAtual('confirmacao');
                setEmail(''); 
            } else {
                
                setModal({
                    isOpen: true,
                    type: 'error',
                    title: 'Erro na inscrição',
                    message: resposta.message || 'Ocorreu um erro ao realizar a inscrição'
                });
            }
        } catch (error: any) {
            console.error('Erro na inscrição:', error);
            
            const mensagemErro = error.message || '';
            
            if (mensagemErro.includes('já está cadastrado')) {
                setModal({
                    isOpen: true,
                    type: 'error',
                    title: 'Email já cadastrado',
                    message: 'Este email já está inscrito na nossa newsletter.'
                });
            } else if (mensagemErro.includes('Failed to fetch') || mensagemErro.includes('Network')) {
                setModal({
                    isOpen: true,
                    type: 'error',
                    title: 'Erro de conexão',
                    message: 'Não foi possível conectar ao servidor. Verifique se o back-end está rodando na porta 8080.'
                });
            } else {
                setModal({
                    isOpen: true,
                    type: 'error',
                    title: 'Erro na inscrição',
                    message: mensagemErro || 'Ocorreu um erro ao realizar a inscrição'
                });
            }
        }
    };

    return (
        <div className="container-inscricao">
            {telaAtual === 'formulario' ? (
                <div className="card-preto">
                    <img 
                        src={ImagemMindtech} 
                        alt="Mindtech" 
                        className="imagem-mindtech"
                    />
                    
                    <img 
                        src={LogoMindtech} 
                        alt="Mindtech Logo" 
                        className="logo-mindtech"
                    />

                    <div className="conteudo-formulario">
                        <h1 className="titulo-principal">Inscreva-se agora!</h1>
                        
                        <p className="descricao">
                            Preencha o formulário abaixo para se inscrever e comece a receber 
                            nossas atualizações diretamente em sua caixa de entrada.
                        </p>

                        <div className="beneficios">
                            <div className="beneficio">
                                <img src={IconeCheck} alt="Check" className="icone-check" />
                                <div className="texto-beneficio">
                                    <strong>Guias e Tutoriais:</strong> Aprenda como implementar e 
                                    otimizar soluções de IoT para sua empresa.
                                </div>
                            </div>
                            <div className="beneficio">
                                <img src={IconeCheck} alt="Check" className="icone-check" />
                                <div className="texto-beneficio">
                                    <strong>Notícias e Tendências:</strong> Fique por dentro das 
                                    últimas novidades e avanços no mundo do IoT.
                                </div>
                            </div>
                            <div className="beneficio">
                                <img src={IconeCheck} alt="Check" className="icone-check" />
                                <div className="texto-beneficio">
                                    <strong>Ofertas e Promoções:</strong> Receba ofertas especiais e 
                                    promoções exclusivas para assinantes da nossa newsletter.
                                </div>
                            </div>
                        </div>

                        <form onSubmit={handleSubmit} className="formulario">
                            <div className="campo-email">
                                <label htmlFor="email">E-mail</label>
                                <input
                                    type="email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    placeholder="email@email.com"
                                    required
                                />
                            </div>

                            <div className="linha-divisoria"></div>

                            <button type="submit" className="botao-inscrever">
                                Inscrever-se
                            </button>
                        </form>
                    </div>
                </div>
            ) : (
                <TelaConfirmacao onVoltar={() => setTelaAtual('formulario')} />
            )}

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

export default FormularioInscricao;