export interface RespostaInscricao {
    timestamp: string;
    status: number;
    message: string;
    object: any;
}

export interface DadosFormularioInscricao {
    email: string;
}