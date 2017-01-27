package net.batchu.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by i1551 on 1/12/2017.
 */
@Component
public class ResponseMessageBuilder {

    public Map<String, Object> createmsg(MessageType messageType, String msg) {

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put(messageType.toString(), msg);
        return errorMap;
    }

    public Map<String, Object> createmsg(MessageType messageType, String msg, Object o) {

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put(messageType.toString(), msg);
        errorMap.put("Successfully added", o);
        return errorMap;
    }

    public Map<String, String> getErrors(BindingResult bindingResult) {

        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;

    }

    public enum MessageType {
        ERROR, WARN
    }
}
