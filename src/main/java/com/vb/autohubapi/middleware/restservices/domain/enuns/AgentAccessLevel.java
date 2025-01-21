package com.vb.autohubapi.middleware.restservices.domain.enuns;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum AgentAccessLevel {

    BASIC("basic"),
    EVALUATOR("evaluator"),
    ADMINISTRATOR("administrator");

    private String value;

    private AgentAccessLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AgentAccessLevel{" +
                "value='" + value + '\'' +
                '}';
    }
}
