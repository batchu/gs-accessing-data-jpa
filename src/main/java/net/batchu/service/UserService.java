package net.batchu.service;

import net.batchu.model.User;
import net.batchu.model.exception.NoMatchingUserException;

import java.util.List;

/**
 * Created by i1551 on 1/27/2017.
 */
public interface UserService {

    List<User> findAll();

    User findById(Long id) throws NoMatchingUserException;
}
