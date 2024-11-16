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
public class CarCreateResponseDTO {

    private static final long serialVersionUID = 1L;

    @JsonProperty("carId")
    private Long carId;

    @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    @JsonProperty("placa")
    private String placa;

}
