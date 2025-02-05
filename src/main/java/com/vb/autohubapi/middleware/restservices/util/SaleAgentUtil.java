package com.vb.autohubapi.middleware.restservices.util;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.AgentUpdateResponseDTO;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
@Slf4j
public class SaleAgentUtil {

    public SaleAgentResponseDTO buildSaleAgentResponse(SaleAgentEntity saleAgent){

        log.info("Starting method buildSaleAgentResponseDTO: {}", saleAgent.getId());
        SaleAgentResponseDTO agentResponseDTO = new SaleAgentResponseDTO();
        agentResponseDTO.setFirstName(saleAgent.getFirstName());
        agentResponseDTO.setLastName(saleAgent.getLastName());
        agentResponseDTO.setEmail(saleAgent.getEmail());
        agentResponseDTO.setPhone(saleAgent.getPhone());
        agentResponseDTO.setHireDate(LocalDate.now());
        agentResponseDTO.setAccessLevel(saleAgent.getAccessLevel());

        return agentResponseDTO;
    }

    public AgentUpdateResponseDTO buildAgentUpdateResponseDTO(SaleAgentEntity saleAgentDTO){

        log.info("Starting method buildAgentUpdateResponseDTO: {}", saleAgentDTO.getId());
        AgentUpdateResponseDTO updateResponseDTO = new AgentUpdateResponseDTO();

        updateResponseDTO.setFirstName(saleAgentDTO.getFirstName());
        updateResponseDTO.setLastName(saleAgentDTO.getLastName());
        updateResponseDTO.setDhUpdate(LocalDateTime.now());
        updateResponseDTO.setPhone(saleAgentDTO.getPhone());
        updateResponseDTO.setEmail(saleAgentDTO.getEmail());

        log.info("retorno metodo buildAgentUpdateResponseDTO: {}", saleAgentDTO.getId());
        return updateResponseDTO;
    }
}
