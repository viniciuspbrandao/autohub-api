package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.mysql.SaleAgentRepository;

import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class SalesAgentServiceImpl implements ISalesAgentService {

    @Autowired
    SaleAgentRepository repository;

    @Transactional
    public SaleAgentEntity saveNewAgentSale(SaleAgentEntity saleAgent) throws Exception {

        SaleAgentEntity agent = new SaleAgentEntity();
        agent.setFirstName(saleAgent.getFirstName());
        agent.setLastName(saleAgent.getLastName());
        agent.setEmail(saleAgent.getEmail());
        agent.setPhone(saleAgent.getPhone());
        agent.setPassword(saleAgent.getPassword());
        agent.setBirthDate(LocalDateTime.now());
        agent.setHireDate(LocalDateTime.now());
        agent.setAccessLevel(saleAgent.getAccessLevel());
        agent.setSalary(saleAgent.getSalary());
        agent.setCommission(saleAgent.getCommission());
        agent.setCreatedAt(LocalDateTime.now());
        agent.setUpdatedAt(LocalDateTime.now());
        agent.setStatus(1);

        try {
            log.info("Creating a new AgentSale");

            repository.save(agent);
            return agent;

        } catch (Exception e){

        }
        return null;
    }

}
