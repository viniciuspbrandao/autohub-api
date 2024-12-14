package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;

import java.util.List;


public interface ISalesAgentService {

    SaleAgentResponseDTO saveNewAgentSale(SaleAgentEntity saleAgent) throws Exception;

    List<SaleAgentEntity> getAllSaleAgentEntityActiveTrue();
}
