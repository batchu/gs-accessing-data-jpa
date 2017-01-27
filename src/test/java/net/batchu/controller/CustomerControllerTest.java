package net.batchu.controller;

import net.batchu.dao.CustomerRepository;
import net.batchu.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by i1551 on 1/26/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerRepository repository;
    PodamFactory factory;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        factory = new PodamFactoryImpl();
    }

    @Test
    public void getCustomers() throws Exception {

        List<Customer> customers = new ArrayList<Customer>();
        customers.add(factory.manufacturePojo(Customer.class));
        customers.add(factory.manufacturePojo(Customer.class));

        when(repository.findAll()).thenReturn(customers);

        this.mockMvc.perform(get("/customer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(equalTo(customers.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName").value(equalTo(customers.get(0).getLastName())));

    }

}