package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import com.vb.autohubapi.middleware.restservices.infra.RequestsExceptionHandler;
import com.vb.autohubapi.middleware.restservices.postgresql.CarRepository;
import com.vb.autohubapi.middleware.restservices.util.CarUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.vb.autohubapi.middleware.restservices.util.ConstantesUtil.LIMIT_YEAR;


@Slf4j
@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    CarRepository repository;

    @Autowired
    CarUtil carUtil;

    @Transactional
    public CarCreateResponseDTO saveCar(CarEntity newCar) throws Exception {

        CarEntity carEntity = createCarEntity(newCar);
        return carUtil.buildCreateCarCreateResponseObject(carEntity);

    }


    private CarEntity createCarEntity(CarEntity carDTO) throws Exception {

        try {
            log.info("Creating a new car");

            checkIfCarExistInDB(carDTO);
            checkCarAgeForSalesPotential(carDTO);

            CarEntity carEntity = new CarEntity(carDTO);

            carEntity.setCreatedDate(LocalDateTime.now());
            carEntity.setDhUpdate(LocalDateTime.now());

            repository.save(carEntity);
            return carEntity;

        } catch (RequestsExceptionHandler e) {
            log.error("Error" + e);
            throw new RuntimeException(e);
        }
    }


    public List<CarEntity> getAllCarsActiveTrue() {
        log.info("inicio metodo: getAllCarsActiveTrue");
        return repository.findAllByActiveTrue();
    }


    private void checkCarAgeForSalesPotential(CarEntity newCar) throws RequestsExceptionHandler {
        if (newCar == null || newCar.getAno() < LIMIT_YEAR) {
            log.error("Invalid car: " + newCar);
            throw new RequestsExceptionHandler();
        } else {
            log.info("The car is relatively new and has good sales potential based on its age.");
        }
    }

    private void checkIfCarExistInDB(CarEntity dto) throws RequestsExceptionHandler {

        if (dto != null && !dto.getPlaca().trim().isEmpty()) {

            String placaNewCar = dto.getPlaca().trim();
            Optional<CarEntity> optionalPlaca = repository.findByPlaca(placaNewCar);

            if (optionalPlaca.isPresent()) {
                log.info("A car with the plate '{}' already exists in the database.", placaNewCar);
                throw new DataIntegrityViolationException(placaNewCar);
            }
            log.info("No car found in the database with the plate '{}'.", placaNewCar);

        } else {
            log.info("Received a null car object or an empty plate. Car details: {}", dto);
            throw new RuntimeException(String.valueOf(dto));
        }
    }
}
