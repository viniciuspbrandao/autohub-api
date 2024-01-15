package com.vb.autohubapi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404() {
        return ResponseEntity.badRequest().body("Dado nao encontrado.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity threatRunTimeExpeption() {
        return ResponseEntity.badRequest().body("Dados invalidos.\n" +
                "Verifique a regra para inclusao de carros:\n" +
                "* Ano de fabricacao abaixo de 2015, nao pode ser aceito.");
    }
}
