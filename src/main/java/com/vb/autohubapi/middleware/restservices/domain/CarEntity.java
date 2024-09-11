package com.vb.autohubapi.middleware.restservices.domain;


import com.vb.autohubapi.middleware.restservices.controller.CarDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity(name= "cars")
@Table(name= "cars_v3")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    private int ano;

    private float preco;

    private String cor;
    @Column(name = "PLACA_CARRO")
    private String placa;

    @Column(name = "dt_create")
    private LocalDate createdDate;

    @Column(name = "dh_update")
    private LocalDateTime dhUpdate;

    private boolean active; //todos os novos carros inseridos recebem o status de ativo

    public CarEntity(CarEntity car) {
        this.marca = car.marca;
        this.modelo = car.modelo;
        this.ano = car.ano;
        this.preco = car.preco;
        this.cor = car.cor;
        this.placa = car.placa;
        this.active = true;
        this.createdDate = car.createdDate;
        this.dhUpdate = car.dhUpdate;
    }


    public CarEntity(CarDTO dto) {
        this.marca = dto.getMarca();
        this.modelo = dto.getModelo();
        this.ano = dto.getAno();
        this.preco = dto.getPreco();
        this.cor = dto.getCor();
        this.placa = dto.getPlaca();
        this.active = true;
    }

    public CarEntity(CarCreateResponseDTO dto) {
        this.placa = dto.getPlaca();
        this.createdDate = dto.getCreatedDate();
        this.active = true;
    }


    //sobrescrita do toString
    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", preco=" + preco +
                ", cor='" + cor + '\'' +
                ", placa='" + placa + '\'' +
                ", data de criacao='" + createdDate + '\'' +
                '}';
    }
}