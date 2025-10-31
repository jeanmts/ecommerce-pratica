package org.serratec.ecommerce.exception;

public class ClienteNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ClienteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
