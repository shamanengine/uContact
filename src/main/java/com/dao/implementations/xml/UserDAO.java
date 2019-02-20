package com.dao.implementations.xml;

import com.dao.entities.Role;
import com.dao.entities.User;
import com.dao.implementations.xml.helpers.PhoneBook;
import com.dao.implementations.xml.helpers.XmlManager;
import com.dao.interfaces.RoleIDAO;
import com.dao.interfaces.UserIDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 21.11.2016.
 */
@Repository
public class UserDAO implements UserIDAO<User> {

    @Autowired
    PhoneBook phoneBook;
    @Autowired
    XmlManager xmlManager;
    @Autowired
    RoleIDAO roleIDAO;
    @Autowired
    BCryptPasswordEncoder encoder;

    /*XmlManager xmlManager = new XmlManager();
    PhoneBook phoneBook = xmlManager.getPhoneBook();
    RoleIDAO roleIDAO = new RoleDAO();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();*/

    @Override
    public List<User> findAll() {
        return phoneBook.getUsers();
    }

    @Override
    public User findOne(Long id) {
        List<User> users = phoneBook.getUsers();
        for (User user : users) {
            if (user.getUserId().equals(id))
                return user;
        }
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        List<User> users = phoneBook.getUsers();
        for (User user : users) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    @Override
    // @Transactional
    public User saveUser(User user) {
        List<User> users = phoneBook.getUsers();
        User persistedUser = null;
        int ind = users.indexOf(user);

        if (ind != -1) {
            persistedUser = users.get(ind);
            persistedUser.initialize(user);
        } else {
            persistedUser = new User();

            Long userNextId = phoneBook.getIdTrace().getUserNextId();
            persistedUser.setUserId(userNextId);
            phoneBook.getIdTrace().setUserNextId(++userNextId);

            persistedUser.initialize(user);
            users.add(persistedUser);
        }

        // We need to hash password every time that we reset it
        persistedUser.setPassword(encoder.encode(persistedUser.getPassword()));

        Role userRole = persistedUser.getRole();

        // default role for new user
        if (userRole == null) {
            userRole = roleIDAO.findOne(Role.USER_ROLE_ID);
            persistedUser.setRole(userRole);
        }

        if (!userRole.getUserList().contains(persistedUser)) {
            userRole.getUserList().add(persistedUser);
        }

        if (persistedUser.getCreateTime() == null)
            persistedUser.setCreateTime(LocalDateTime.now());

        xmlManager.marshal();

        return persistedUser;
    }

}
