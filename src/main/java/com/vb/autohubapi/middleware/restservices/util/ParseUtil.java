package com.vb.autohubapi.middleware.restservices.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

@Slf4j
public class ParseUtil {

    private ParseUtil() {
    }

    public static Integer parseIntegerOrNull (Long value){
        return value != null ? value.intValue() : null;
    }

    public static Integer parseIntegerOrNull (String value){
        Integer out;
        try {
            out = (value != null && !value.trim().isEmpty()) ? Integer.parseInt(value) : null;
        } catch (Exception e) {
            out = null;
        }
        return out;
    }

    public static BigDecimal nullToZero (BigDecimal value){
        return value == null ? BigDecimal.ZERO : value;
    }

    public static BigDecimal parseBigDecimalOrNull(Float value){
        return value != null ? BigDecimal.valueOf(value) : null;
    }

    public static boolean isNullOrZero(Object obj){
        return obj == null || ObjectUtils.isEmpty(obj);
    }
}
