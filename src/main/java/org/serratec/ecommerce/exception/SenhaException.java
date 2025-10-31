package org.serratec.ecommerce.exception;

public class SenhaException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public SenhaException(String mensagem) {
        super(mensagem);
    }
}
