package com.vb.autohubapi.service;

import com.vb.autohubapi.controller.CarDTO;
import com.vb.autohubapi.domain.CarEntity;
import com.vb.autohubapi.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    @Autowired
    CarRepository repository;


    @Transactional
    public CarEntity saveCar(CarEntity newCar) {

        if (newCar.getAno() < 2015) {
            throw new RuntimeException();
        } else {
            System.out.println("ainda é fabricado");
        }
        return repository.save(newCar);
    }


    public List<CarEntity> getAllActiveTrue() {
        return repository.findAllByActiveTrue();
    }


    @Transactional
    public CarEntity updateCar(Long id, CarEntity updtCar) {
        Optional<CarEntity> optionalCar = repository.findById(id);

        if (optionalCar.isPresent()) {
            CarEntity existingCar = optionalCar.get();
            existingCar.setMarca(updtCar.getMarca());
            existingCar.setModelo(updtCar.getModelo());
            existingCar.setAno(updtCar.getAno());
            existingCar.setPreco(updtCar.getPreco());
            existingCar.setCor(updtCar.getCor());

            System.out.println("dados inseridos"); //log de depuração
            repository.save(existingCar);

            return existingCar;
        } else {
            throw new EntityNotFoundException("Carro não encontrado com o ID: " + id);
        }
    }


    @Transactional
    public CarEntity disableCarById(Long id) {

        Optional<CarEntity> optionalCars = repository.findById(id);
        if (optionalCars.isPresent()) {
            CarEntity desableCar = optionalCars.get();
            desableCar.setActive(false);
            System.out.println("Carro desativado com sucesso. ID: " + id); //log de depuração
            return desableCar;
        } else {
            throw new EntityNotFoundException();
        }
    }


    public CarEntity getCarById(Long id) {
        Optional<CarEntity> optionalCars = repository.findById(id);
        if (optionalCars.isPresent()) {
            CarEntity selectCar = optionalCars.get();
            return repository.getReferenceById(selectCar.getId());

        } else {
            throw new EntityNotFoundException();
        }
    }

}
