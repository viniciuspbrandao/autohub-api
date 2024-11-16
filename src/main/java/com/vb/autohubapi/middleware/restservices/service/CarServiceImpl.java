package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.car.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.car.CarEntity;
import com.vb.autohubapi.middleware.restservices.domain.car.CarUpdateResponseDTO;
import com.vb.autohubapi.middleware.restservices.infra.RequestsExceptionHandler;
import com.vb.autohubapi.middleware.restservices.mysql.CarRepository;
import com.vb.autohubapi.middleware.restservices.util.CarUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

            repository.save(carEntity);
            return carEntity;

        } catch (RequestsExceptionHandler e) {
            log.error("Error" + e);
            throw new RuntimeException(e);
        }
    }


    public List<CarEntity> getAllCarsActiveTrue() {
        log.info("Starting method to retrieve all active cars.");
        return repository.findAllByActiveTrue();
    }


    @Transactional
    public CarUpdateResponseDTO updateCar(Long id, CarEntity carDTO){
        log.info("Initiating update for car with ID: {}", id);
        CarEntity carEntity = updateCarInDataBase(id, carDTO);
        log.info("Car with ID: {} has been successfully updated.", id);
        return carUtil.buildCarUpdateResponseDTO(carEntity);
    }


    @Transactional
    public CarEntity updateCarInDataBase(Long id, CarEntity carDTO) {

        if (carDTO == null || String.valueOf(carDTO).isEmpty()) {
            throw new IllegalArgumentException("O objeto CarEntity deve ser valido.");
        }

        log.info("Searching for car with ID: {}", id);
        CarEntity uptCar = repository.findById(id).orElseThrow(()-> new RuntimeException("Car not found with ID: " + id));

        log.info("Car found: {}", uptCar);

        uptCar.setId(carDTO.getId());
        uptCar.setBrand(carDTO.getBrand());
        uptCar.setModel(carDTO.getModel());
        uptCar.setYear(carDTO.getYear());
        uptCar.setPreco(carDTO.getPreco());
        uptCar.setCor(carDTO.getCor());
        uptCar.setPlaca(carDTO.getPlaca());
        uptCar.setDhUpdate(LocalDateTime.now());
        uptCar.setQuilometragem(carDTO.getQuilometragem());
        uptCar.setCombustivel(carDTO.getCombustivel());
        uptCar.setTransmissao(carDTO.getTransmissao());
        uptCar.setNumPortas(carDTO.getNumPortas());

        log.info("Updating car with new details: {}", uptCar);
        return repository.save(uptCar);

    }

    private void checkCarAgeForSalesPotential(CarEntity newCar) throws RequestsExceptionHandler {
        if (newCar == null || newCar.getYear() < LIMIT_YEAR) {
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

    public CarEntity getCarById(Long id){
        log.info("Initiating search for vehicle with ID: {}", id);
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Vehicle not found with ID: " + id));
    }

    public ResponseEntity<Void> disableCarById(Long id){

        return repository.findById(id)
                .map(recordFound -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow();
    }
}
