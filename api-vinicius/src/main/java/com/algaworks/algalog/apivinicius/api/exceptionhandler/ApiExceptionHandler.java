package com.algaworks.algalog.apivinicius.api.exceptionhandler;

import com.algaworks.algalog.apivinicius.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.apivinicius.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problema.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Problema.Campo(nome, mensagem));
        }

        Problema problema = new Problema(status.value(),
                OffsetDateTime.now(),
                "Um ou mais campos estão inválidos",
                campos);
        return this.handleExceptionInternal(ex, problema,headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
        HttpStatus status =HttpStatus.BAD_REQUEST;
        Problema problema = new Problema(status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null);
        return handleExceptionInternal(ex,problema,new HttpHeaders(), status, request);

    }
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request){
        HttpStatus status =HttpStatus.NOT_FOUND;
        Problema problema = new Problema(status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null);
        return handleExceptionInternal(ex,problema,new HttpHeaders(), status, request);

    }
}
