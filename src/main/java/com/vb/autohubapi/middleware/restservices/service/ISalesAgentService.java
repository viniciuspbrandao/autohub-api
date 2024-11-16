package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.car.CarCreateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.car.CarEntity;
import com.vb.autohubapi.middleware.restservices.domain.car.CarUpdateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ISalesAgentService {

    SaleAgentEntity saveNewAgentSale(SaleAgentEntity saleAgent) throws Exception;

}
