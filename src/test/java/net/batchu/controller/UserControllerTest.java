package net.batchu.controller;

import net.batchu.dao.UserRepository;
import net.batchu.model.User;
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
public class UserControllerTest {

    @Mock
    UserRepository repository;

    PodamFactory factory;

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        factory = new PodamFactoryImpl();
    }

    @Test
    public void getCustomers() throws Exception {

        List<User> users = new ArrayList<User>();
        users.add(factory.manufacturePojo(User.class));
        users.add(factory.manufacturePojo(User.class));

        when(repository.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(equalTo(users.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName").value(equalTo(users.get(0).getLastName())));

    }

}