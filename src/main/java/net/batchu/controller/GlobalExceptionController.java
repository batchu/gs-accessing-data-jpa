package net.batchu.controller;

/**
 * Created by i1551 on 1/27/2017.
 */

import net.batchu.model.exception.NoMatchingUserException;
import net.batchu.model.exception.Response;
import net.batchu.util.ResponseMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;


@ControllerAdvice
public class GlobalExceptionController {


    @Autowired
    ResponseMessageBuilder responseMessageBuilder;

    @ExceptionHandler(NoMatchingUserException.class)
    public ResponseEntity<?> handleCustomException(NoMatchingUserException e) {

        Response response = new Response();
        response.setMessage(e.getMessage());

        ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity.status(e.getHttpStatus()).body(responseMessageBuilder.createmsg(ResponseMessageBuilder.MessageType.ERROR, e.getErrorMsg()));

        return responseEntity;
    }


}