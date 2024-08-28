package com.vb.autohubapi.middleware.restservices.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarDTO {

    private final Long id;
    private final String marca;
    private final String modelo;
    private final int ano;
    private final float preco;
    private final String cor;

    private final String placa;
    public CarDTO(
            Long id,

            @NotBlank
            String marca,

            @NotBlank
            String modelo,

            @NotNull
            int ano,

            @NotNull
            float preco,

            @NotBlank
            String cor,

            @NotBlank
            String placa) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
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
}
