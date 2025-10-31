package org.serratec.ecommerce.exception;

public class CategoriaNaoEncontradaException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CategoriaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
