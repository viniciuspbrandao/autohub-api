package com.vb.autohubapi.middleware.restservices.util;

import com.vb.autohubapi.middleware.restservices.domain.car.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.car.CarEntity;
import com.vb.autohubapi.middleware.restservices.domain.car.CarUpdateResponseDTO;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Component
public class CarUtil {
    public CarCreateResponseDTO buildCreateCarCreateResponseObject(CarEntity carEntity) {
        log.info("Creating a CarCreateResponseObject: {}", carEntity.getId());
        CarCreateResponseDTO createResponseDTO = new CarCreateResponseDTO();
        createResponseDTO.setCarId(carEntity.getId());
        createResponseDTO.setPlaca(carEntity.getPlaca());
        createResponseDTO.setCreatedDate(LocalDateTime.now());

        return createResponseDTO;
    }

    public CarUpdateResponseDTO buildCarUpdateResponseDTO(CarEntity car){

        log.info("Starting method buildCarUpdateResponseDTO: {}", car.getId());
        CarUpdateResponseDTO updateResponseDTO = new CarUpdateResponseDTO();

        updateResponseDTO.setCarId(car.getId());
        updateResponseDTO.setDhUpdate(LocalDateTime.now());
        updateResponseDTO.setPlaca(car.getPlaca());

        log.info("retorno metodo buildCarUpdateResponseDTO: {}", car.getId());
        return updateResponseDTO;
    }

}
