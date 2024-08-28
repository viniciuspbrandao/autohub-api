package com.vb.autohubapi.middleware.restservices.controller;

import com.vb.autohubapi.middleware.restservices.api.CarApi;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import com.vb.autohubapi.middleware.restservices.service.CarServiceImpl;
import com.vb.autohubapi.middleware.restservices.service.ICarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carros")
public class CarController implements CarApi {

    @Autowired
    private ICarService iService;
//    private CarServiceImpl service;


//    @Operation(summary = "List all active cars")
//    @GetMapping
//    public ResponseEntity listAllCarsActives() {
//        var activeCars = service.getAllActiveTrue();
//        return ResponseEntity.ok(activeCars);
//    }

    @Operation(summary = "Create a new car")
    @PostMapping
    public ResponseEntity insertCar(@RequestBody @Valid CarDTO carDTO) throws Exception {
        System.out.println("Recebendo dados do carro: " + carDTO);
//      Cria uma nova instância de CarsEntity a partir de carDTO, salva usando o serviço saveCar e armazena a entidade salva em savedCar.
        CarEntity newCar = iService.saveCar(new CarEntity(carDTO));
        return ResponseEntity.ok(newCar); //Qd nao tem corpo no retorno precisa do ".ok().build()"
    }

//    @Operation(summary = "Update a car")
//    @PutMapping("/{id}")
//    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody @Valid CarDTO carDTO) {
//        CarEntity updtCar = service.updateCar(id, new CarEntity(carDTO));
//        return ResponseEntity.ok(updtCar);
//    }

//    @Operation(summary = "Deactivate/Delete a car")
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteCar(@PathVariable Long id) {
//        CarEntity desactiviedCar = service.disableCarById(id);
//        return ResponseEntity.noContent().build();
//    }

//    @Operation(summary = "Get car by id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the car"),
////            @ApiResponse(responseCode = "201", description = "Car created"),
//            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
//            @ApiResponse(responseCode = "404", description = "Car not found")})
//    @GetMapping("/{id}")
//    public ResponseEntity getById(@PathVariable Long id) {
//        CarEntity carEntity = service.getCarById(id);
//        return ResponseEntity.ok().body(carEntity);
//    }
}
