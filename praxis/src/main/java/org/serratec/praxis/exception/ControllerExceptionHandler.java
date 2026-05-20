package org.serratec.praxis.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tools.jackson.databind.exc.ValueInstantiationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            erros.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ErroResposta erroResposta = new ErroResposta(status.value(),
                "Existem Campos Inválidos, confira o preenchimento", LocalDateTime.now(), erros);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String mensagem = "Requisição inválida. Verifique o formato dos dados enviados.";

        if (ex.getCause() instanceof ValueInstantiationException vie) {
            if (vie.getCause() instanceof EnumValidationException eve) {
                mensagem = eve.getMessage();
            }
        }

        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                mensagem,
                LocalDateTime.now(),
                new ArrayList<>()
        );

        return ResponseEntity.badRequest().body(erroResposta);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResposta> handleResourceNotFound(ResourceNotFoundException ex) {

        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                new ArrayList<>()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
    }

    @ExceptionHandler(EmailException.class)
    protected ResponseEntity<ErroResposta> handleEmailException(EmailException ex) {


        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                new ArrayList<>()
        );
        return ResponseEntity.unprocessableEntity().body(erroResposta);
    }

    @ExceptionHandler(CpfException.class)
    protected ResponseEntity<ErroResposta> handleCpfException(CpfException ex) {

        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                new ArrayList<>()
        );
        return ResponseEntity.unprocessableEntity().body(erroResposta);
    }

}
