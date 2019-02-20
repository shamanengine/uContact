package com.dao.implementations.sdatajpa;

import com.dao.entities.Contact;
import com.dao.entities.User;
import com.dao.interfaces.ContactIDAO;
import com.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 07.11.2016.
 */
@Repository
public class ContactDAO implements ContactIDAO<Contact> {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact findOne(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> findContactsByUser(User user) {
        return contactRepository.findContactsByUser(user);
    }

    @Transactional
    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public void delete(Long id) {
        contactRepository.delete(id);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    // == SEARCH BEGIN ==

    // Search by User

    @Override
    public List<Contact> findByUserAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String firstNamePattern) {
        return contactRepository.findByUserAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(
                user, firstNamePattern);
    }

    @Override
    public List<Contact> findByUserAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String lastNamePattern) {
        return contactRepository.findByUserAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(
                user, lastNamePattern);
    }

    @Override
    public List<Contact> findByUserAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String patronymicPattern) {
        return contactRepository.findByUserAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(
                user, patronymicPattern);
    }

    @Override
    public List<Contact> findByUserAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String cellPhonePattern) {
        return contactRepository.findByUserAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(
                user, cellPhonePattern);
    }

    @Override
    public List<Contact> findByUserAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String homePhonePattern) {
        return contactRepository.findByUserAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(
                user, homePhonePattern);
    }

    @Override
    public List<Contact> findByUserAndAddressContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String addressPattern) {
        return contactRepository.findByUserAndAddressContainingIgnoreCaseOrderByFirstNameAsc(
                user, addressPattern);
    }

    @Override
    public List<Contact> findByUserAndEmailContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String emailPattern) {
        return contactRepository.findByUserAndEmailContainingIgnoreCaseOrderByFirstNameAsc(
                user, emailPattern);
    }

// Search by UserId

    @Override
    public List<Contact> findByUserUserIdAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String firstNamePattern) {
        return contactRepository.findByUserUserIdAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(
                userId, firstNamePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String lastNamePattern) {
        return contactRepository.findByUserUserIdAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(
                userId, lastNamePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String patronymicPattern) {
        return contactRepository.findByUserUserIdAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(
                userId, patronymicPattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String cellPhonePattern) {
        return contactRepository.findByUserUserIdAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(
                userId, cellPhonePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String homePhonePattern) {
        return contactRepository.findByUserUserIdAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(
                userId, homePhonePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndAddressContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String addressPattern) {
        return contactRepository.findByUserUserIdAndAddressContainingIgnoreCaseOrderByFirstNameAsc(
                userId, addressPattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndEmailContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String emailPattern) {
        return contactRepository.findByUserUserIdAndEmailContainingIgnoreCaseOrderByFirstNameAsc(
                userId, emailPattern);
    }

    // == SEARCH END ==
}
