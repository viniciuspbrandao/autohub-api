package com.vb.autohubapi.middleware.restservices.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentListDataDTO;
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



    @Operation(summary = "Get agent by Id")
    @GetMapping
    public ResponseEntity<SaleAgentEntity> getAgentById(@PathVariable @NotNull @Positive Long id);

    @Operation(summary = "Disable Agent")
    @DeleteMapping
    public ResponseEntity disableAgentById(@PathVariable @NotNull @Positive Long id);


    @Operation(summary = "List agents by status")
    @GetMapping
    public ResponseEntity<SaleAgentListDataDTO> listAgentsByStatus(
            @RequestParam(value = "statusId", required = false) Long statusId) throws JsonProcessingException;

}
