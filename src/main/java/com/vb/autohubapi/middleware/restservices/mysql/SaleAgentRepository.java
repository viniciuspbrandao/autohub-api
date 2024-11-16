package com.vb.autohubapi.middleware.restservices.mysql;

import com.vb.autohubapi.middleware.restservices.domain.saleagent.SaleAgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleAgentRepository extends JpaRepository<SaleAgentEntity,Long> {
}
