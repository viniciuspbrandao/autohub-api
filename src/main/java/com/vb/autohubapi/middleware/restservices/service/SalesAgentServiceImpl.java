package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import com.vb.autohubapi.middleware.restservices.mysql.SaleAgentRepository;
import com.vb.autohubapi.middleware.restservices.util.SaleAgentUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Slf4j
@Service
public class SalesAgentServiceImpl implements ISalesAgentService {

    @Autowired
    SaleAgentRepository repository;

    @Autowired
    SaleAgentUtil agentUtil;

    @Transactional
    public SaleAgentResponseDTO saveNewAgentSale(SaleAgentEntity saleAgent) throws Exception {
        SaleAgentEntity agent = creatingNewAgentSale(saleAgent);
        return agentUtil.buildSaleAgentResponse(agent);
    }


    private SaleAgentEntity creatingNewAgentSale(SaleAgentEntity saleAgentDTO) throws Exception {

        try {
            log.info("Creating a new AgentSale");
            SaleAgentEntity agent = new SaleAgentEntity();
            agent.setFirstName(saleAgentDTO.getFirstName());
            agent.setLastName(saleAgentDTO.getLastName());
            agent.setEmail(saleAgentDTO.getEmail());
            agent.setPhone(saleAgentDTO.getPhone());
            agent.setPassword(saleAgentDTO.getPassword());
            agent.setBirthDate(saleAgentDTO.getBirthDate());
            agent.setHireDate(LocalDate.now());
            agent.setAccessLevel(saleAgentDTO.getAccessLevel());
            agent.setSalary(saleAgentDTO.getSalary());
            agent.setCommission(saleAgentDTO.getCommission());
            agent.setCreatedAt(LocalDateTime.now());
    //        agent.setUpdatedAt(LocalDateTime.now());
            agent.setStatus(1);

            repository.save(agent);

        return agent;
           } catch (Exception e){
            log.error("Error" + e);
            throw new Exception(e);
        }
    }

}
