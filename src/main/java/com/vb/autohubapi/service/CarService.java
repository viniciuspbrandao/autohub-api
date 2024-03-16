package com.vb.autohubapi.service;

import com.vb.autohubapi.domain.CarEntity;
import com.vb.autohubapi.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    @Autowired
    CarRepository repository;


    @Transactional
    public CarEntity saveCar(CarEntity newCar) {
        checkFabricationYear(newCar);
        checkDuplicationCar(newCar);
        return repository.save(newCar);
    }


    //aplicar validacao de placa para utilizar nos testes

    public List<CarEntity> getAllActiveTrue() {
        return repository.findAllByActiveTrue();
    }


    @Transactional
    public CarEntity updateCar(Long id, CarEntity updtCar) { //aplicar a validacao de placa duplicada
        Optional<CarEntity> optionalCar = repository.findById(id);

        if (optionalCar.isPresent()) {
            CarEntity existingCar = optionalCar.get();
            existingCar.setMarca(updtCar.getMarca());
            existingCar.setModelo(updtCar.getModelo());
            existingCar.setAno(updtCar.getAno());
            existingCar.setPreco(updtCar.getPreco());
            existingCar.setCor(updtCar.getCor());
            existingCar.setPlaca(updtCar.getPlaca());
            existingCar.setActive(true);

            System.out.println("dados atualizados");
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

    private void checkFabricationYear(CarEntity newCar) {
        if (newCar != null && newCar.getAno() < 2015) {
            throw new RuntimeException();
        } else {
            System.out.println("Este veícula ainda é fabricado");
        }
    }

    private void checkDuplicationCar(CarEntity dto) {
        String placaNewCar = dto.getPlaca();
        Optional<CarEntity> optionalPlaca = repository.findByPlaca(dto.getPlaca());

        if (optionalPlaca.isPresent()) {
            throw new DataIntegrityViolationException(placaNewCar);
        } else {
            System.out.println("Este é um novo carro. Nao consta no banco de dados.");
        }
    }
}
