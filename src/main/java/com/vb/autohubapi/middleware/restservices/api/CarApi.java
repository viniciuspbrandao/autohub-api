package com.vb.autohubapi.middleware.restservices.api;


import com.vb.autohubapi.middleware.restservices.controller.CarDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CarApi {

    @Operation(summary = "Create a new car")
    @PostMapping
    public ResponseEntity<CarDTO> insertCar(@RequestBody @Valid CarDTO carDTO) throws Exception;


}
