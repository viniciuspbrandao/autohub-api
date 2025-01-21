package com.vb.autohubapi.middleware.restservices.domain.saleagent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vb.autohubapi.middleware.restservices.domain.enuns.AgentAccessLevel;
import com.vb.autohubapi.middleware.restservices.domain.enuns.converters.AgentAccessLevelConverter;

import jakarta.persistence.Convert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleAgentResponseDTO {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("hire_date")
    private LocalDate hireDate;

    @JsonProperty("access_level")
    @Convert(converter = AgentAccessLevelConverter.class)
    private AgentAccessLevel accessLevel;


    @Override
    public String toString() {
        return "SaleAgentResponseDTO{" +
                "Name='" + firstName + " " + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hireDate=" + hireDate +
                ", accessLevel=" + accessLevel +
                '}';
    }

}
