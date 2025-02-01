package com.vb.autohubapi.middleware.restservices.service;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentListDataDTO;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentResponseDTO;
import com.vb.autohubapi.middleware.restservices.mysql.SaleAgentRepository;
import com.vb.autohubapi.middleware.restservices.util.SaleAgentUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    public SaleAgentEntity getAgentById(Long id){
        log.info("Initiating search for Agent with ID: {}", id);
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Agent not found with ID: " + id));
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
            agent.setActive(true);

            repository.save(agent);

        return agent;
           } catch (Exception e){
            log.error("Error" + e);
            throw new Exception(e);
        }
    }

    public ResponseEntity<Void> disableAgentById(Long id){

        return repository.findById(id)
                .map(recordFound -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow();
    }

    @Override
    public SaleAgentListDataDTO listAgentsByStatus(Long status) {

        List<SaleAgentEntity> saleAgents;

        if (status != null) {
            saleAgents = repository.findAgentsByStatus(status);
        } else {
            saleAgents = repository.findAll();
        }

        int pageSize = 20;  // Exemplo de tamanho de página, isso pode ser variável ou configurável
        int totalRegisters = saleAgents.size();

        SaleAgentListDataDTO response = new SaleAgentListDataDTO();
        response.setTotalRegisters(totalRegisters);
        response.setSize(pageSize);
        response.setSaleAgentList(mapToDTO(saleAgents));

        response.setNextPage(totalRegisters > pageSize);
        response.setPreviewPage(false);

        return response;
    }

    private List<SaleAgentResponseDTO> mapToDTO(List<SaleAgentEntity> saleAgents) {
        List<SaleAgentResponseDTO> saleAgentDTOs = new ArrayList<>();

        for (SaleAgentEntity agent : saleAgents) {
            SaleAgentResponseDTO dto = new SaleAgentResponseDTO();
            dto.setFirstName(agent.getFirstName());
            dto.setLastName(agent.getLastName());
            dto.setEmail(agent.getEmail());
            dto.setPhone(agent.getPhone());
            dto.setHireDate(agent.getHireDate());
            dto.setAccessLevel(agent.getAccessLevel());
            saleAgentDTOs.add(dto);
        }

        return saleAgentDTOs;
    }

}
