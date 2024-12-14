package com.vb.autohubapi.middleware.restservices.mysql;

import com.vb.autohubapi.middleware.restservices.domain.AgentAccessLevel;
import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleAgentRepository extends JpaRepository<SaleAgentEntity,Long> {

    @Query(value = "SELECT * FROM tb_sales_users_v3 WHERE status = 1", nativeQuery = true)
    List<SaleAgentEntity> findAllActiveAgents();

    List<SaleAgentEntity> findAll();

}
