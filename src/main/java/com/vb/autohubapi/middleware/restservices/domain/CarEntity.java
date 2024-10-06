package com.vb.autohubapi.middleware.restservices.domain;


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

    @Column(name = "placa_carro", unique = true)
    private String placa;

    @Column(name = "dh_create")
    private LocalDateTime createdDate;

    @Column(name = "dh_update")
    private LocalDateTime dhUpdate;

    private boolean active;

    @Column(name = "mileage")
    private int quilometragem;

    @Column(name = "tipo_combustivel")
    private String combustivel;

    @Column(name = "transmissao")
    private String transmissao;

    @Column(name = "numero_portas")
    private int numPortas;


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
        this.quilometragem = car.quilometragem;
        this.combustivel = car.combustivel;
        this.transmissao = car.transmissao;
        this.numPortas = car.numPortas;
    }


    public CarEntity(CarDTO dto) {
        this.marca = dto.getMarca();
        this.modelo = dto.getModelo();
        this.ano = dto.getAno();
        this.preco = dto.getPreco();
        this.cor = dto.getCor();
        this.placa = dto.getPlaca();
        this.active = true;
        this.quilometragem = dto.getQuilometragem();
        this.combustivel = dto.getCombustivel();
        this.transmissao = dto.getTransmissao();
        this.numPortas = dto.getNumPortas();
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
                ", numero de portas='" + numPortas + '\'' +
                '}';
    }
}