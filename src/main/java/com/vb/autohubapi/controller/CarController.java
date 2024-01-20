package com.vb.autohubapi.controller;

import com.vb.autohubapi.domain.CarEntity;
import com.vb.autohubapi.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carros")
public class CarController {

    @Autowired
    private CarService service;


    @GetMapping
    public ResponseEntity listAllCarsActives() {
        var activeCars = service.getAllActiveTrue();
        return ResponseEntity.ok(activeCars);
    }

    @PostMapping
    public ResponseEntity insertCar(@RequestBody @Valid CarDTO carDTO) {
        System.out.println("Recebendo dados do carro: " + carDTO);
//      Cria uma nova instância de CarsEntity a partir de carDTO, salva usando o serviço saveCar e armazena a entidade salva em savedCar.
        CarEntity newCar = service.saveCar(new CarEntity(carDTO));
        return ResponseEntity.ok(newCar); //Qd nao tem corpo no retorno precisa do ".ok().build()"
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody @Valid CarDTO carDTO) {
        CarEntity updtCar = service.updateCar(id, new CarEntity(carDTO));
        return ResponseEntity.ok(updtCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        CarEntity desactiviedCar = service.disableCarById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        CarEntity carEntity = service.getCarById(id);
        return ResponseEntity.ok().body(carEntity);
    }
}
