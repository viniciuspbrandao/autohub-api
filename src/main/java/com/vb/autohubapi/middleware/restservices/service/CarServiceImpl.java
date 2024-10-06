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

//    private CarEntity persistCarDates(CarEntity newCar) {
//
//        CarEntity dates = new CarEntity(newCar);
//        newCar.setCreatedDate(LocalDate.now());
//        dates.setDhUpdate(LocalDateTime.now());
//
//        return repository.save(dates);
//
//    }

    // TODO: 9/12/2024
    //  O valor do carro no cadastro é o preco q foi comprado pela agencia
    //  Acrescentar uma margem de lucro variavel de acordo com ano, marca e preco do carro.

    private CarEntity createCarEntity(CarEntity carDTO) throws Exception {

        try {
            log.info("creating a new car");

            checkIfCarExistInDB(carDTO);
            checkCarAgeForSalesPotential(carDTO);

            CarEntity carEntity = new CarEntity(carDTO);

            carEntity.setCreatedDate(LocalDateTime.now());
            carEntity.setDhUpdate(LocalDateTime.now());

            repository.save(carEntity);
            return carEntity;

        } catch (RequestsExceptionHandler e) {
            log.error("erro" + e);
            throw new RuntimeException(e);
        }
    }

//    @Transactional
//    public CarEntity createNewCarObject() throws Exception{
//        log.info("criando objeto CarEntity");
//
//        CarEntity carEntity = new CarEntity();
//        carEntity.setCreatedDate(LocalDate.now());
//
//        return carEntity;
//    }




    public List<CarEntity> getAllCarsActiveTrue() {
        return repository.findAllByActiveTrue();
    }


//    @Transactional
//    public CarEntity updateCar(Long id, CarEntity updtCar) { //aplicar a validacao de placa duplicada
//        Optional<CarEntity> optionalCar = repository.findById(id);
//
//        if (optionalCar.isPresent()) {
//            CarEntity existingCar = optionalCar.get();
//            existingCar.setMarca(updtCar.getMarca());
//            existingCar.setModelo(updtCar.getModelo());
//            existingCar.setAno(updtCar.getAno());
//            existingCar.setPreco(updtCar.getPreco());
//            existingCar.setCor(updtCar.getCor());
//            existingCar.setPlaca(updtCar.getPlaca());
//            existingCar.setActive(true);
//
//            System.out.println("dados atualizados");
//            repository.save(existingCar);
//
//            return existingCar;
//        } else {
//            throw new EntityNotFoundException("Carro não encontrado com o ID: " + id);
//        }
//    }


//    @Transactional
//    public CarEntity disableCarById(Long id) {
//
//        Optional<CarEntity> optionalCars = repository.findById(id);
//        if (optionalCars.isPresent()) {
//            CarEntity desableCar = optionalCars.get();
//            desableCar.setActive(false);
//            System.out.println("Carro desativado com sucesso. ID: " + id); //log de depuração
//            return desableCar;
//        } else {
//            throw new EntityNotFoundException();
//        }
//    }
//
//
//    public CarEntity getCarById(Long id) {
//        Optional<CarEntity> optionalCars = repository.findById(id);
//        if (optionalCars.isPresent()) {
//            CarEntity selectCar = optionalCars.get();
//            return repository.getReferenceById(selectCar.getId());
//
//        } else {
//            throw new EntityNotFoundException();
//        }
//    }

    private void checkCarAgeForSalesPotential(CarEntity newCar) throws RequestsExceptionHandler {
        if (newCar == null || newCar.getAno() < LIMIT_YEAR) {
            log.error("Carro inválido: " + newCar);
            throw new RequestsExceptionHandler();
        } else {
            System.out.println("The car is relatively new and has good sales potential based on its age.");
        }
    }

    private void checkIfCarExistInDB(CarEntity dto) throws RequestsExceptionHandler {

        String placaNewCar = dto.getPlaca().trim();
        Optional<CarEntity> optionalPlaca = repository.findByPlaca(placaNewCar);

        if (optionalPlaca.isPresent()) {
            log.info("Carro encontrado");
            throw new DataIntegrityViolationException(placaNewCar);
        } else {
            log.info("Carro não encontrado");
            System.out.println("Este é um novo carro. Nao consta no banco de dados.");
        }
    }
}
