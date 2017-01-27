package net.batchu.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by i1551 on 1/12/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order")
public class NoMatchingUserException extends UserGenericException {


    public NoMatchingUserException(String message) {
        super(message);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
    }

}
