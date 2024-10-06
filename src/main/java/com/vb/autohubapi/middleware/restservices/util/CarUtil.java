package com.vb.autohubapi.middleware.restservices.util;

import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CarUtil {
    public CarCreateResponseDTO buildCreateCarCreateResponseObject(CarEntity carEntity) {

        CarCreateResponseDTO createResponseDTO = new CarCreateResponseDTO();
        createResponseDTO.setCarId(carEntity.getId());
        createResponseDTO.setPlaca(carEntity.getPlaca());
        createResponseDTO.setCreatedDate(LocalDateTime.now());

        return createResponseDTO;
    }
}
