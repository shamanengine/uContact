package com.services;


import com.dao.interfaces.UserIDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
// @Transactional
public class UserDetailsServiceImplementation implements UserDetailsService {
   /* @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.dao.entities.User userEntity = userRepository.findUserByLogin(login);
        if (userEntity == null)
            throw new UsernameNotFoundException(login + " not found");

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userEntity.getRole().getTitle()));

        return new User(userEntity.getLogin(), userEntity.getPassword(), roles);
    }*/
    @Autowired
    // @Qualifier("UserDAO")
    private UserIDAO userIDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.dao.entities.User userEntity = userIDAO.findUserByLogin(login);
        if (userEntity == null)
            throw new UsernameNotFoundException(login + " not found");

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userEntity.getRole().getTitle()));

        return new User(userEntity.getLogin(), userEntity.getPassword(), roles);
    }

}