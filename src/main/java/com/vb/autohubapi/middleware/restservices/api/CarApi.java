package com.vb.autohubapi.middleware.restservices.api;


import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import com.vb.autohubapi.middleware.restservices.domain.CarUpdateResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CarApi {

    @Operation(summary = "Create a new car")
    @PostMapping
    public ResponseEntity<CarCreateResponseDTO> insertCar(@RequestBody @Valid CarEntity carDTO) throws Exception;

    @Operation(summary = "List all active cars")
    @GetMapping
    public ResponseEntity<CarEntity> getAllCarsActiveTrue();

    @Operation(summary = "Update car in database")
    @PutMapping
    public ResponseEntity<CarUpdateResponseDTO> updateCar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CarEntity carDTO);

}
