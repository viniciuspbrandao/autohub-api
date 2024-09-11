package com.vb.autohubapi.middleware.restservices.postgresql;

import com.vb.autohubapi.middleware.restservices.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findAllByActiveTrue();

    //@Query("SELECT c FROM CarEntity c WHERE c.placa = :placa")
    Optional<CarEntity> findByPlaca(@Param("placa") String placa);
    /*
     * find
     * All
     * By
     * Active
     * True
     *Seguindo uma nomenclatura padronizada o JPA, consegue construir uma query onde busca de acordo com a nomenclatura.*/
}