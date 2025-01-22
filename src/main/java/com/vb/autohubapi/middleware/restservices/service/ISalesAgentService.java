package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ISalesAgentService {

    SaleAgentResponseDTO saveNewAgentSale(SaleAgentEntity saleAgent) throws Exception;

    List<SaleAgentEntity> getAllSaleAgentEntityActiveTrue();

    ResponseEntity disableAgentById(Long id);

    SaleAgentEntity getAgentById(Long id);
}
