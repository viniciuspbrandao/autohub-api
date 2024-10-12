package com.vb.autohubapi.middleware.restservices.controller;

import com.vb.autohubapi.middleware.restservices.api.CarApi;
import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import com.vb.autohubapi.middleware.restservices.service.ICarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController implements CarApi {

    @Autowired
    private ICarService iService;


    @Operation(summary = "List all active cars")
    @GetMapping
    public ResponseEntity getAllCarsActiveTrue() {
        List<CarEntity> activeCars = iService.getAllCarsActiveTrue();
        return ResponseEntity.ok(activeCars);
    }

    @Operation(summary = "Create a new car")
    @PostMapping
    @Transactional
    public ResponseEntity<CarCreateResponseDTO> insertCar(@RequestBody @Valid CarEntity dto) throws Exception {
        return new ResponseEntity<>(this.iService.saveCar(dto), HttpStatusCode.valueOf(201));
    }

    @Operation(summary = "Update a car")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateCar(@PathVariable Long id, @RequestBody @Valid CarEntity carDTO) {
        return new ResponseEntity<>(this.iService.updateCar(id, carDTO), HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Get car by id")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found the car"),
    @ApiResponse(responseCode = "201", description = "Car created"),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
    @ApiResponse(responseCode = "404", description = "Car not found")})
    @GetMapping("/{id}")
    public ResponseEntity getCarById(@PathVariable Long id) {
        CarEntity carEntity = iService.getCarById(id);
        return ResponseEntity.ok(carEntity);
    }
}
