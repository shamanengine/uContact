package com.dao.interfaces;

import com.dao.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 05.11.2016.
 */
@Component
public interface UserIDAO<S extends User> {
    List<S> findAll();

    S findOne(Long id);

    S findUserByLogin(String login);

    S saveUser(User user);
}
