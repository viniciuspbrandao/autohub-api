package com.vb.autohubapi.domain;


import com.vb.autohubapi.controller.CarDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity(name= "cars")
@Table(name= "cars")
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

    private boolean active; //todos os novos carros inseridos recebem o status de ativo

    public CarEntity(CarDTO dto) {
        this.marca = dto.marca();
        this.modelo = dto.modelo();
        this.ano = dto.ano();
        this.preco = dto.preco();
        this.cor = dto.cor();
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
                '}';
    }
}