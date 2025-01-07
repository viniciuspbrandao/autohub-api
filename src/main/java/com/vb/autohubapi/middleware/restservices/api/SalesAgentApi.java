package com.vb.autohubapi.middleware.restservices.api;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SalesAgentApi {

    @Operation(summary = "Create a Agent Sale")
    @PostMapping
    public ResponseEntity<SaleAgentResponseDTO> saveNewAgentSale(@RequestBody @Valid SaleAgentEntity saleAgent) throws Exception;


    @Operation(summary = "List active agents")
    @GetMapping
    public ResponseEntity<SaleAgentEntity> listActiveAgents();

    @Operation(summary = "Disable Agent")
    @DeleteMapping
    public ResponseEntity disableAgentById(@PathVariable @NotNull @Positive Long id);

}
