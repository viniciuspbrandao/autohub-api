package com.vb.autohubapi.middleware.restservices.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CarDTO {

    private final Long id;
    private final String marca;
    private final String modelo;
    private final int ano;
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
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
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
