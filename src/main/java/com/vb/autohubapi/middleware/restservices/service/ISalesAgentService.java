package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.AgentUpdateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentListDataDTO;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ISalesAgentService {

    SaleAgentResponseDTO saveNewAgentSale(SaleAgentEntity saleAgent) throws Exception;


    ResponseEntity disableAgentById(Long id);

    SaleAgentEntity getAgentById(Long id);

    SaleAgentListDataDTO listAgentsByStatus(Long status);

    AgentUpdateResponseDTO updateAgent(Long id, SaleAgentEntity agentDTO);
}
