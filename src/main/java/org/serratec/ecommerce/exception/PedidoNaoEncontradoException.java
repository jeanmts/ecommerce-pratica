package org.serratec.ecommerce.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
