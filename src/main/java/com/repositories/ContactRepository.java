package com.repositories;

import com.dao.entities.Contact;
import com.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 04.11.2016.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Override
    Contact getOne(Long id);

    @Override
    List<Contact> findAll();

    @Override
    Contact findOne(Long id);

    List<Contact> findContactsByUser(User user);

    @Override
    <S extends Contact> S saveAndFlush(S contact);

    @Override
    void delete(Long aLong);

    @Override
    void delete(Contact entity);

    // == SEARCH BEGIN ==

    // Search by User
    /**
     * @param user
     * @param firstNamePattern
     * @return
     */
    List<Contact> findByUserAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String firstNamePattern);

    /**
     * @param user
     * @param lastNamePattern
     * @return
     */
    List<Contact> findByUserAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String lastNamePattern);

    /**
     * @param user
     * @param patronymicPattern
     * @return
     */
    List<Contact> findByUserAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String patronymicPattern);

    /**
     * @param user
     * @param cellPhonePattern
     * @return
     */
    List<Contact> findByUserAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String cellPhonePattern);

    /**
     * @param user
     * @param homePhonePattern
     * @return
     */
    List<Contact> findByUserAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String homePhonePattern);

    /**
     * @param user
     * @param addressPattern
     * @return
     */
    List<Contact> findByUserAndAddressContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String addressPattern);

    /**
     * @param user
     * @param emailPattern
     * @return
     */
    List<Contact> findByUserAndEmailContainingIgnoreCaseOrderByFirstNameAsc(
            User user, String emailPattern);

    // Search by UserId

    /**
     *
     * @param userId
     * @param firstNamePattern
     * @return
     */
    List<Contact> findByUserUserIdAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String firstNamePattern);

    /**
     *
     * @param userId
     * @param lastNamePattern
     * @return
     */
    List<Contact> findByUserUserIdAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String lastNamePattern);

    /**
     *
     * @param userId
     * @param patronymicPattern
     * @return
     */
    List<Contact> findByUserUserIdAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String patronymicPattern);

    /**
     *
     * @param userId
     * @param cellPhonePattern
     * @return
     */
    List<Contact> findByUserUserIdAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String cellPhonePattern);

    /**
     *
     * @param userId
     * @param homePhonePattern
     * @return
     */
    List<Contact> findByUserUserIdAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String homePhonePattern);

    /**
     *
     *
     * @param userId
     * @param addressPattern
     * @return
     */
    List<Contact> findByUserUserIdAndAddressContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String addressPattern);

    /**
     *
     * @param userId
     * @param emailPattern
     * @return
     */
    List<Contact> findByUserUserIdAndEmailContainingIgnoreCaseOrderByFirstNameAsc(
            Long userId, String emailPattern);

    // == SEARCH END ==
}
