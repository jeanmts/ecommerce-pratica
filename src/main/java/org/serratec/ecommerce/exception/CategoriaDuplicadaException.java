package org.serratec.ecommerce.exception;

public class CategoriaDuplicadaException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public CategoriaDuplicadaException(String mensagem) {
        super(mensagem);
    }

}
