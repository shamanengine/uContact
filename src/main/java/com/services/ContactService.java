package com.services;

import com.dao.entities.Contact;
import com.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 11.11.2016.
 */
@Service
@Transactional
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public List<Contact> findByUserIdAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String firstNamePattern) {
        return contactRepository.findByUserUserIdAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(userId, firstNamePattern);
    }
    /*
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;*/
}
