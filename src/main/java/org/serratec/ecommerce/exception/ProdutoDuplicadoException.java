package org.serratec.ecommerce.exception;

public class ProdutoDuplicadoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProdutoDuplicadoException(String message) {
        super(message);
    }
}
