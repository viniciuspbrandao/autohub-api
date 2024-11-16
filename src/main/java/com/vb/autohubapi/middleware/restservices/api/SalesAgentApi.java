package com.vb.autohubapi.middleware.restservices.api;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SalesAgentApi {

    @Operation(summary = "Create a Agent Sale")
    @PostMapping
    public ResponseEntity<SaleAgentEntity> saveNewAgentSale(@RequestBody @Valid SaleAgentEntity saleAgent) throws Exception;


}
