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

//        CarCreateResponseDTO responseDTO = carUtil.buildCreateCarCreateResponseObject(carEntity);

        return carUtil.buildCreateCarCreateResponseObject(carEntity);// carEntity. repository.save(newCar);
    }

    private CarEntity persistCarDates(CarEntity newCar) {

        CarEntity dates = new CarEntity(newCar);

        newCar.setCreatedDate(LocalDate.now());
        dates.setDhUpdate(LocalDateTime.now());

        return repository.save(dates);
    }

    //o valor do carro no cadastro é o preco q foi comprado
    //acrescentar uma margem de lucro variavel de acordo com o ano do carro.

    private CarEntity createCarEntity(CarEntity carDTO) throws Exception {

        try {
            log.info("creating a new car");

            checkIfCarExistInDB(carDTO);
            checkFabricationYear(carDTO);

            CarEntity carEntity = new CarEntity(carDTO);

            carEntity.setCreatedDate(LocalDate.now());
            carEntity.setDhUpdate(LocalDateTime.now());

            repository.save(carEntity);
            return carEntity;

        } catch (RequestsExceptionHandler e) {
            log.error("erro" + e);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public CarEntity createNewCarObject() throws Exception{
        log.info("criando objeto CarEntity");

        CarEntity carEntity = new CarEntity();
        carEntity.setCreatedDate(LocalDate.now());

        return carEntity;
    }


    //aplicar validacao de placa para utilizar nos testes

//    public List<CarEntity> getAllActiveTrue() {
//        return repository.findAllByActiveTrue();
//    }
//
//
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
//
//
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

    private void checkFabricationYear(CarEntity newCar) throws RequestsExceptionHandler {
        if (newCar != null && newCar.getAno() < LIMIT_YEAR) {
            throw new RequestsExceptionHandler();
        } else {
            System.out.println("Este veícula ainda é fabricado");
        }
    }

    private void checkIfCarExistInDB(CarEntity dto) throws RequestsExceptionHandler {
        String placaNewCar = dto.getPlaca();
        Optional<CarEntity> optionalPlaca = repository.findByPlaca(placaNewCar);

        if (optionalPlaca.isPresent()) {
            throw new DataIntegrityViolationException(placaNewCar);
        } else {
            System.out.println("Este é um novo carro. Nao consta no banco de dados.");
        }
    }
}
