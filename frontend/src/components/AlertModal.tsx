import React from 'react';
import '../styles/AlertModal.css';

interface AlertModalProps {
    isOpen: boolean;
    type: 'success' | 'error' | 'info';
    title: string;
    message: string;
    onClose: () => void;
}

const AlertModal: React.FC<AlertModalProps> = ({ 
    isOpen, 
    type, 
    title, 
    message, 
    onClose 
}) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                <div className={`modal-icon modal-icon-${type}`}>
                    {type === 'success' && '✓'}
                    {type === 'error' && '✕'}
                    {type === 'info' && 'ⓘ'}
                </div>
                
                <h3 className="modal-title">{title}</h3>
                <p className="modal-message">{message}</p>
                
                <button 
                    className={`modal-button modal-button-${type}`}
                    onClick={onClose}
                >
                    Fechar
                </button>
            </div>
        </div>
    );
};

export default AlertModal;