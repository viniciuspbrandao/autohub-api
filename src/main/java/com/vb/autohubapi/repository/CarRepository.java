package com.vb.autohubapi.repository;
import com.vb.autohubapi.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findAllByActiveTrue();
    Optional<CarEntity> findByPlaca(String placa);
    /*
     * find
     * All
     * By
     * Active
     * True
     *Seguindo uma nomenclatura padronizada o JPA, consegue construir uma query onde busca de acordo com a nomenclatura.*/
}