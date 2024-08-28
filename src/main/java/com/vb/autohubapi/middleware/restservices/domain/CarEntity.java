package com.vb.autohubapi.middleware.restservices.domain;


import com.vb.autohubapi.middleware.restservices.controller.CarDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity(name= "cars")
@Table(name= "cars_v2")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pq o ID nao pode ser int ??? dá erro na JPA se for int

    private String marca;

    private String modelo;

    private int ano;

    private float preco;

    private String cor;
    @Column(name = "PLACA_CARRO")
    private String placa;

    private boolean active; //todos os novos carros inseridos recebem o status de ativo

    public CarEntity(CarDTO dto) {
        this.marca = dto.getMarca();
        this.modelo = dto.getModelo();
        this.ano = dto.getAno();
        this.preco = dto.getPreco();
        this.cor = dto.getCor();
        this.placa = dto.getPlaca();
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
                '}';
    }
}