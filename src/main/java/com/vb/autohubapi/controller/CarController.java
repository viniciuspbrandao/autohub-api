package com.vb.autohubapi.controller;

import com.vb.autohubapi.domain.CarEntity;
import com.vb.autohubapi.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarController {

    @Autowired
    private CarRepository repository;

    @GetMapping
    public ResponseEntity listAllCarsActives() {
        var allCars = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allCars);
    }

    @PostMapping
    @Transactional
    public ResponseEntity insertCar(@RequestBody @Valid CarDTO carDTO) {
        System.out.println("Recebendo dados do carro: " + carDTO);
        CarEntity newCar = new CarEntity(carDTO);
        repository.save(newCar);
        System.out.println("Carro salvo com sucesso: " + newCar);
        return ResponseEntity.ok().build(); //Qd nao tem corpo no retorno precisa do ".ok().build()"
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCar(@RequestBody @Valid CarDTO carDTO) {
        System.out.println("Recebendo dados do carro: " + carDTO); //log de depuração
        //service
        Optional<CarEntity> optionalCar = repository.findById(carDTO.id());
        if (optionalCar.isPresent()) {
            CarEntity updatedCar = optionalCar.get(); //ERREI aqui, ao usar o ".findById", precisa do "Optional<>", do ".isPresent()" e do ".get()"
            updatedCar.setMarca(carDTO.marca());
            updatedCar.setModelo(carDTO.modelo());
            updatedCar.setAno(carDTO.ano());
            updatedCar.setPreco(carDTO.preco());
            updatedCar.setCor(carDTO.cor());
            System.out.println("dados inseridos: " + updatedCar); //log de depuração
            return ResponseEntity.ok().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCar(@PathVariable Long id) {
        System.out.println("Request de inativacao do carro de Id: " + id); //log de depuração

        Optional<CarEntity> optionalCars = repository.findById(id);
        if (optionalCars.isPresent()) {
            CarEntity removeCar = optionalCars.get(); //ERREI aqui, ao usar o ".findById", precisa do "Optional<>", do ".isPresent()" e do ".get()"
            removeCar.setActive(false);
            System.out.println("Carro inativado com sucesso. ID: " + id); //log de depuração

            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {

        Optional<CarEntity> optionalCars = repository.findById(id);
        if (optionalCars.isPresent()) {
            CarEntity selectCar = optionalCars.get(); //ERREI aqui, ao usar o ".findById", precisa do "Optional<>", do ".isPresent()" e do ".get()"
            return ResponseEntity.ok(selectCar);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
