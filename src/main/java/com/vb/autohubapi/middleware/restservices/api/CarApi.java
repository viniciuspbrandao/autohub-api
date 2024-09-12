package com.vb.autohubapi.middleware.restservices.api;


import com.vb.autohubapi.middleware.restservices.domain.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CarApi {

    @Operation(summary = "Create a new car")
    @PostMapping
    public ResponseEntity<CarCreateResponseDTO> insertCar(@RequestBody @Valid CarEntity carDTO) throws Exception;

    @Operation(summary = "List all active cars")
    @GetMapping
    public ResponseEntity<CarEntity> getAllCarsActiveTrue();
}
