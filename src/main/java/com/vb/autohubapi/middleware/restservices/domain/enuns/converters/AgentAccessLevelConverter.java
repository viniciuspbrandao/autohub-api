package com.vb.autohubapi.middleware.restservices.domain.enuns.converters;

import com.vb.autohubapi.middleware.restservices.domain.enuns.AgentAccessLevel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AgentAccessLevelConverter implements AttributeConverter<AgentAccessLevel, String> {
    @Override
    public String convertToDatabaseColumn(AgentAccessLevel agentAccessLevel) {
        if (agentAccessLevel == null) {
            return null;
        }
        return agentAccessLevel.getValue();
    }

    @Override
    public AgentAccessLevel convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(AgentAccessLevel.values())
                .filter(accessLevel -> accessLevel.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
