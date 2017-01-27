package net.batchu.controller;

import io.swagger.annotations.ApiOperation;
import net.batchu.model.exception.NoMatchingUserException;
import net.batchu.model.exception.Response;
import net.batchu.model.exception.UserGenericException;
import net.batchu.service.UserService;
import net.batchu.util.ResponseMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by i1551 on 1/26/2017.
 */
@RestController
public class UserController {


    private static Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    private ResponseMessageBuilder responseMessageBuilder;

    @ApiOperation(value = "Get Users", notes = "Retreive all Users", nickname = "Get Users")
    @GetMapping("/user")
    public ResponseEntity<?> getUsers() {

        return ResponseEntity.ok(userService.findAll());
    }

    @ExceptionHandler({NoMatchingUserException.class})
    @ResponseBody
    public ResponseEntity<?> handleTagReferenceLibraryExceptions(UserGenericException e) {

        LOG.error(e.getHttpStatus().toString(), e);
        Response response = new Response();
        response.setMessage(e.getMessage());

        ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity.status(e.getHttpStatus()).body(responseMessageBuilder.createmsg(ResponseMessageBuilder.MessageType.ERROR, e.getErrorMsg()));

        return responseEntity;
    }

}
