package com.dao.implementations.sdatajpa;

import com.dao.entities.Role;
import com.dao.entities.User;
import com.dao.interfaces.UserIDAO;
import com.repositories.RoleRepository;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 05.11.2016.
 */
@Repository
public class UserDAO implements UserIDAO {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        if(user.getRole() == null) {
            user.setRole(roleRepository.findOne(Role.USER_ROLE_ID));
        }


        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
