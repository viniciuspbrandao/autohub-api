package com.vb.autohubapi.middleware.restservices.domain.saleagent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vb.autohubapi.middleware.restservices.domain.AgentAccessLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
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
