package com.vb.autohubapi.middleware.restservices.api;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SalesAgentApi {

    @Operation(summary = "Create a Agent Sale")
    @PostMapping
    public ResponseEntity<SaleAgentResponseDTO> saveNewAgentSale(@RequestBody @Valid SaleAgentEntity saleAgent) throws Exception;


    @Operation(summary = "List active agents")
    @GetMapping
    public ResponseEntity<SaleAgentEntity> listActiveAgents();

}
