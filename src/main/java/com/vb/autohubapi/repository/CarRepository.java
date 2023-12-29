package com.vb.autohubapi.repository;
import com.vb.autohubapi.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<CarEntity, Long> {
}