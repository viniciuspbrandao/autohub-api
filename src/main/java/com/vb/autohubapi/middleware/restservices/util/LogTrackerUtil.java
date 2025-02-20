package com.vb.autohubapi.middleware.restservices.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogTrackerUtil {

    public static final String STRING_EMPTY = "";

    public void writeLog(Object obj, String uId, String method, String details){
        String objId = STRING_EMPTY;

        if (obj != null){
//            if (obj.getAlgo() != null){
//                objId = obj.getAlgo();
//            } else {
//                objId = obj.getOutraCoisa().toString();
//            }
        }

        writeLog(objId, uId,method, details);
    }


    public void writeLog(String objParam, String uIdParam, String methodParam, String detailsParam){
        var objId = cleanLog(objParam);
        var uId = cleanLog(uIdParam);
        var method = cleanLog(methodParam);
        var details = cleanLog(detailsParam);

    }

    private String cleanLog(String value){
        if (value == null) return STRING_EMPTY;
        return cleanEncode(value);
    }

    private String cleanEncode(String value) {
        return value
                .replace('\n','_')
                .replace('\n','_')
                .replace('\n','_');
    }


}
