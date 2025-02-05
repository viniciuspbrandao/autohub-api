package com.vb.autohubapi.middleware.restservices.controller;

import com.vb.autohubapi.middleware.restservices.api.SalesAgentApi;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentListDataDTO;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import com.vb.autohubapi.middleware.restservices.service.ISalesAgentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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

    @Operation(summary = "Get agent by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the agent"),
            @ApiResponse(responseCode = "201", description = "agent created"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "agent not found")})
    @GetMapping("/{id}")
    public ResponseEntity getAgentById(@PathVariable Long id) {
        SaleAgentEntity saleAgent = iSalesAgentService.getAgentById(id);
        return ResponseEntity.ok(saleAgent);
    }

    @Operation(summary = "Deactivate/Delete a AgentById")
    @DeleteMapping("/{id}")
    public ResponseEntity disableAgentById(@PathVariable Long id) {
        return new ResponseEntity<>(this.iSalesAgentService.disableAgentById(id), HttpStatusCode.valueOf(204));
    }

    @Operation(summary = "List agents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "201", description = ""),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = "")})
    @GetMapping("/listagents")
    public ResponseEntity<SaleAgentListDataDTO> listAgentsByStatus(
            @RequestParam(value = "statusId", required = false) Long statusId) {
        SaleAgentListDataDTO response = iSalesAgentService.listAgentsByStatus(statusId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update a Agent")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateAgent(@PathVariable Long id, @RequestBody @Valid SaleAgentEntity agenteDTO) {
        return new ResponseEntity<>(this.iSalesAgentService.updateAgent(id, agenteDTO), HttpStatusCode.valueOf(200));
    }

}
