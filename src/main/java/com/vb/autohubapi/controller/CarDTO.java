package com.vb.autohubapi.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarDTO {

    private final Long id;
    private final String marca;
    private final String modelo;
    private final int ano;
    private final float preco;
    private final String cor;

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
            String cor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
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
}
