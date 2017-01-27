package net.batchu.service;

import net.batchu.dao.UserRepository;
import net.batchu.model.User;
import net.batchu.model.exception.NoMatchingUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i1551 on 1/27/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        repository.findAll().forEach(a -> {
            users.add(a);
        });

        return users;
    }

    @Override
    public User findById(Long id) throws NoMatchingUserException {
        User user = repository.findById(id);
        if (user == null)
            throw new NoMatchingUserException("No user found with id " + id);
        return user;
    }
}
