import { RespostaInscricao, DadosFormularioInscricao } from '../types/newsletter';

const URL_BASE_API = 'http://localhost:8080';

export const apiNewsletter = {
    async inscrever(email: string): Promise<RespostaInscricao> {
        const resposta = await fetch(`${URL_BASE_API}/mindtech-newsletter/inscricao`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email }),
        });
        
        const dados = await resposta.json();
        
        if (!resposta.ok) {
            throw new Error(dados.message || 'Erro na inscrição');
        }
        
        return dados;
    },

    async cancelarInscricao(email: string): Promise<RespostaInscricao> {
        const resposta = await fetch(
            `${URL_BASE_API}/mindtech-newsletter/cancelar-inscricao?email=${encodeURIComponent(email)}`,
            {
                method: 'DELETE',
            }
        );
        
        const dados = await resposta.json();
        
        if (!resposta.ok) {
            throw new Error(dados.message || 'Erro no cancelamento');
        }
        
        return dados;
    },
};