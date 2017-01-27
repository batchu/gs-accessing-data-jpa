package net.batchu.controller;

import net.batchu.model.User;
import net.batchu.model.exception.NoMatchingUserException;
import net.batchu.service.UserService;
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
import org.springframework.web.util.NestedServletException;
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
    UserService userService;

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

        when(userService.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(equalTo(users.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName").value(equalTo(users.get(0).getLastName())));

    }

    @Test
    public void getUser() throws Exception, NoMatchingUserException {

        Long id = 6L;
        User user = factory.manufacturePojo(User.class);
        when(userService.findById(id)).thenReturn(user);

        this.mockMvc.perform(get("/user/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(equalTo(user.getFirstName())))
                .andExpect(jsonPath("$.lastName").value(equalTo(user.getLastName())));

    }

    @Test(expected = NestedServletException.class)
    public void getUser_throwsException_forInvalidUser() throws NoMatchingUserException, Exception {

        Long id = 6L;
        when(userService.findById(id)).thenThrow(new NoMatchingUserException("No Matching user found"));
        this.mockMvc.perform(get("/user/" + id));

    }
}