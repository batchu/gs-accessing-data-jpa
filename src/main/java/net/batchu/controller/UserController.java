package net.batchu.controller;

import io.swagger.annotations.ApiOperation;
import net.batchu.model.exception.NoMatchingUserException;
import net.batchu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i1551 on 1/26/2017.
 */
@RestController
public class UserController {


    private static Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @ApiOperation(value = "Get Users", notes = "Retreive all Users", nickname = "Get Users")
    @GetMapping("/user")
    public ResponseEntity<?> getUsers() {

        return ResponseEntity.ok(userService.findAll());
    }

    @ApiOperation(value = "Get User By ID", notes = "Retreive User by ID", nickname = "Get a User")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws NoMatchingUserException {

        return ResponseEntity.ok(userService.findById(id));
    }


}
