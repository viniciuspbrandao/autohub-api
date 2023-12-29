package com.vb.autohubapi.controller;

import com.vb.autohubapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class CarController {

    @Autowired
    private CarRepository repository;

    @GetMapping
    public ResponseEntity listAllCars() {

        System.out.println("Entrando no metodo listAllCars");
        var allCars = repository.findAll();
        System.out.println("Listagem de carros concluida. Total de carros: " + allCars.size());
        return ResponseEntity.ok(allCars);

    }

}
