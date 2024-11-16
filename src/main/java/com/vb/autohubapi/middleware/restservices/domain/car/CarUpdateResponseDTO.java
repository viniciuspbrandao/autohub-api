package com.vb.autohubapi.middleware.restservices.domain.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateResponseDTO {

    @JsonProperty("carId")
    private Long carId;

    @JsonProperty("dh_update")
    private LocalDateTime dhUpdate;

    @JsonProperty("placa")
    private String placa;
}
