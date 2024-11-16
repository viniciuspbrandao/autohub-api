package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.car.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.car.CarEntity;
import com.vb.autohubapi.middleware.restservices.domain.car.CarUpdateResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ICarService {

    CarCreateResponseDTO saveCar(CarEntity newCar) throws Exception;

    List<CarEntity> getAllCarsActiveTrue();

    CarUpdateResponseDTO updateCar(Long id, CarEntity updtCar);

    CarEntity getCarById(Long id);

    ResponseEntity disableCarById(Long id);
}
