package net.batchu.model.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by i1551 on 1/13/2017.
 */
public class UserGenericException extends Throwable {

    String errorMsg = "Generic exception while processing your request. See the HTTP response code for any additional information if made available";


    private HttpStatus httpStatus;

    public UserGenericException(String message) {

        super(message);
        this.errorMsg = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
