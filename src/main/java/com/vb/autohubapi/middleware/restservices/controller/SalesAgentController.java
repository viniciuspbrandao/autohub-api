package com.vb.autohubapi.middleware.restservices.controller;

import com.vb.autohubapi.middleware.restservices.api.SalesAgentApi;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import com.vb.autohubapi.middleware.restservices.service.ISalesAgentService;
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
@RequestMapping("/saleagent")
public class SalesAgentController implements SalesAgentApi {

    @Autowired
    private ISalesAgentService iSalesAgentService;


    @Operation(summary = "save New Agent Sale")
    @PostMapping
    @Transactional
    public ResponseEntity<SaleAgentResponseDTO> saveNewAgentSale(@RequestBody @Valid SaleAgentEntity saleAgent) throws Exception {
        return new ResponseEntity<>(this.iSalesAgentService.saveNewAgentSale(saleAgent), HttpStatusCode.valueOf(201));
    }


}
