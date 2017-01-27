package net.batchu.controller;

import io.swagger.annotations.ApiOperation;
import net.batchu.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i1551 on 1/26/2017.
 */
@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @ApiOperation(value = "Get Users", notes = "Retreive all Users", nickname = "Get Users")
    @GetMapping("/user")
    public ResponseEntity<?> getUsers() {

        return ResponseEntity.ok(repository.findAll());
    }
}
