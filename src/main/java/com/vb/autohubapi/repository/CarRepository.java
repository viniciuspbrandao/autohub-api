package com.vb.autohubapi.repository;
import com.vb.autohubapi.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findAllByActiveTrue();
    /*
     * find
     * All
     * By
     * Active
     * True
     *Seguindo uma nomenclatura padronizada o JPA, consegue construir uma query onde busca de acordo com a nomenclatura.*/
}