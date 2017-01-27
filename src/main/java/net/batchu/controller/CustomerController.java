package net.batchu.controller;

import io.swagger.annotations.ApiOperation;
import net.batchu.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i1551 on 1/26/2017.
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @ApiOperation(value = "Get Customers", notes = "Retreive all Customers", nickname = "Get Customers")
    @GetMapping("/customer")
    public ResponseEntity<?> getCustomers() {

        return ResponseEntity.ok(repository.findAll());
    }
}
