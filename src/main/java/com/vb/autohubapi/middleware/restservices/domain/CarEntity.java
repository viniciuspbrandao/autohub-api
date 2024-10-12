package com.vb.autohubapi.middleware.restservices.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

import static com.vb.autohubapi.middleware.restservices.util.ConstantesUtil.STATUS_ACTIVE;


@Entity(name= "cars")
@Table(name= "cars_v3")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE cars_v3 SET status = 0 WHERE id = ?")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "ano")
    private int ano;

    @Column(name = "preco_aquisicao")
    private float preco;

    @Column(name = "cor")
    private String cor;

    @Column(name = "placa_carro", unique = true)
    private String placa;

    @Column(name = "dh_create")
    private LocalDateTime createdDate;

    @Column(name = "dh_update")
    private LocalDateTime dhUpdate;

    @Column(name = "status")
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
        this.active = STATUS_ACTIVE;
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
        this.active = STATUS_ACTIVE;
        this.quilometragem = dto.getQuilometragem();
        this.combustivel = dto.getCombustivel();
        this.transmissao = dto.getTransmissao();
        this.numPortas = dto.getNumPortas();
    }

    public CarEntity(CarCreateResponseDTO dto) {
        this.placa = dto.getPlaca();
        this.createdDate = dto.getCreatedDate();
        this.active = STATUS_ACTIVE;
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