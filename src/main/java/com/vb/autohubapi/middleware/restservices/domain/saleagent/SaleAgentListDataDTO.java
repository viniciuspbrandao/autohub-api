package com.vb.autohubapi.middleware.restservices.domain.saleagent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Validated
@JsonPropertyOrder
public class SaleAgentListDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("totalRegisters")
    private Integer totalRegisters;

    @JsonProperty("nextPage")
    private Boolean nextPage;

    @JsonProperty("previewPage")
    private Boolean previewPage;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("saleAgentList")
    @Valid
    private List<SaleAgentResponseDTO> saleAgentList;

    public SaleAgentListDataDTO totalRegisters(Integer totalRegisters) {
        this.totalRegisters = totalRegisters;
        return this;
    }
}
