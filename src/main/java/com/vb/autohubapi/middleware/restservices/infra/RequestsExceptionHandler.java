package com.vb.autohubapi.middleware.restservices.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler extends Throwable {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404() {
        return ResponseEntity.badRequest().body("Dado nao encontrado.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity threatRunTimeExpeption() {
        return ResponseEntity.badRequest().body("Dados invalidos.\n" +
                "Verifique a regra para inclusao de carros:\n" +
                "* Se o carro foi fabricado antes do ano 2015, nao pode ser aceito.");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateDataExpeption() {
        return ResponseEntity.badRequest().body("Dados invalidos.\n" +
                "Verifique a regra para inclusao de carros:\n" +
                "* Carros duplicados, nao podem ser aceitos.");
    }
}
