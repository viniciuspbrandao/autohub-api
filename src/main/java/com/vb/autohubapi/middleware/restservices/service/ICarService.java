package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.CarEntity;


public interface ICarService {

    CarEntity saveCar(CarEntity newCar) throws Exception;
}
