package net.batchu.model.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by i1551 on 1/11/2017.
 */
public class UserAlreadyExistsException extends UserGenericException {


    public UserAlreadyExistsException(String message) {

        super(message);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }


}
