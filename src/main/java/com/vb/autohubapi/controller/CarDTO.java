package com.vb.autohubapi.controller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarDTO(

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
        String cor
) {

}
