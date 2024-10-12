package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import com.vb.autohubapi.middleware.restservices.domain.CarUpdateResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ICarService {

    CarCreateResponseDTO saveCar(CarEntity newCar) throws Exception;

    List<CarEntity> getAllCarsActiveTrue();

    CarUpdateResponseDTO updateCar(Long id, CarEntity updtCar);


}
