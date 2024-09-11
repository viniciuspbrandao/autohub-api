package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;


public interface ICarService {

    CarCreateResponseDTO saveCar(CarEntity newCar) throws Exception;
}
