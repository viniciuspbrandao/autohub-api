package com.vb.autohubapi.middleware.restservices.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CarDTO {

    private final Long id;
    private final String brand;
    private final String model;
    private final int year;
    private final float preco;
    private final String cor;
    private final LocalDate data;
    private final String placa;
    private final int quilometragem;
    private final String combustivel;
    private final String transmissao;
    private final int numPortas;


    public CarDTO(
            Long id,

            @NotBlank
            String brand,

            @NotBlank
            String model,

            @NotNull
            int year,

            @NotNull
            float preco,

            @NotBlank
            String cor,

            @NotBlank
            String placa,

            LocalDate data,

            @NotNull
            int quilometragem,

            @NotNull
            String combustivel,

            @NotNull
            String transmissao,

            @NotNull
            int numPortas
            )
    {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.preco = preco;
        this.cor = cor;
        this.placa = placa;
        this.data = data;
        this.quilometragem = quilometragem;
        this.combustivel = combustivel;
        this.transmissao = transmissao;
        this.numPortas = numPortas;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public float getPreco() {
        return preco;
    }

    public String getCor() {
        return cor;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDate getData() {
        return data;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public int getNumPortas() {
        return numPortas;
    }
}
