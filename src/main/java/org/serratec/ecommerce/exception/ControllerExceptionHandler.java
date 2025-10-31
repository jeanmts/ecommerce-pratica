package org.serratec.ecommerce.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            erros.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos invalidos, confira o preenchimento!", LocalDateTime.now(), erros);
        return this.handleExceptionInternal(ex,erroResposta, headers, status, request);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    protected ResponseEntity<Object> handleCategoriaNaoEncontrada(CategoriaNaoEncontradaException ex){
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(CategoriaDuplicadaException.class)
    protected ResponseEntity<Object> handleCategoriaDuplicada(CategoriaDuplicadaException ex){
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    protected ResponseEntity<Object> handleClienteNaoEncontrado(ClienteNaoEncontradoException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(SenhaException.class)
    protected ResponseEntity<Object> handleSenhaException(SenhaException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    protected ResponseEntity<Object> handleClienteDuplicadoException(EmailDuplicadoException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(CpfDuplicadoException.class)
    protected ResponseEntity<Object> handleCpfDuplicadoException(CpfDuplicadoException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    protected ResponseEntity<Object> handleProdutoNaoEncontradoException( ProdutoNaoEncontradoException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }
    @ExceptionHandler(ProdutoDuplicadoException.class)
    protected ResponseEntity<Object> handleProdutoDuplicadoException(ProdutoDuplicadoException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }
}
