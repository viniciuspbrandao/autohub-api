package com.vb.autohubapi.middleware.restservices.controller;

import com.vb.autohubapi.middleware.restservices.api.CarApi;
import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import com.vb.autohubapi.middleware.restservices.service.ICarService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/carros")
public class CarController implements CarApi {

    @Autowired
    private ICarService iService;


//    @Operation(summary = "List all active cars")
//    @GetMapping
//    public ResponseEntity listAllCarsActives() {
//        var activeCars = service.getAllActiveTrue();
//        return ResponseEntity.ok(activeCars);
//    }

    @Operation(summary = "Create a new car")
    @PostMapping
    @Transactional
    public ResponseEntity<CarCreateResponseDTO> insertCar(@RequestBody @Valid CarEntity dto) throws Exception {
        return new ResponseEntity<>(this.iService.saveCar(dto), HttpStatusCode.valueOf(201));

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
