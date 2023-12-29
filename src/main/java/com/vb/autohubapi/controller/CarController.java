package com.vb.autohubapi.controller;

import com.vb.autohubapi.domain.CarEntity;
import com.vb.autohubapi.repository.CarRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @PostMapping
    @Transactional
    public ResponseEntity insertCar(@RequestBody @Valid CarDTO carDTO){
        System.out.println("Recebendo dados do carro: " + carDTO);
        CarEntity newCar = new CarEntity(carDTO);
        repository.save(newCar);
        System.out.println("Carro salvo com sucesso: " + newCar);
        return ResponseEntity.ok().build(); //Qd nao tem corpo no retorno precisa do ".ok().build()"
    }

}
