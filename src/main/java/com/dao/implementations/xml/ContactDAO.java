package com.dao.implementations.xml;

import com.dao.entities.Contact;
import com.dao.entities.User;
import com.dao.implementations.xml.helpers.PhoneBook;
import com.dao.implementations.xml.helpers.XmlManager;
import com.dao.interfaces.ContactIDAO;
import com.dao.interfaces.UserIDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 21.11.2016.
 */
@Repository
public class ContactDAO implements ContactIDAO<Contact> {

    @Autowired
    PhoneBook phoneBook;
    @Autowired
    XmlManager xmlManager;
    @Autowired
    UserIDAO userIDAO;

    /*
    PhoneBook phoneBook = xmlManager.getPhoneBook();
    XmlManager xmlManager = new XmlManager();
    UserIDAO userIDAO = new UserDAO();
    */

    @Override
    public List<Contact> findAll() {
        return phoneBook.getContacts();
    }

    @Override
    public Contact findOne(Long id) {
        List<Contact> contacts = phoneBook.getContacts();
        for (Contact contact : contacts) {
            if (contact.getContactId().equals(id))
                return contact;
        }
        return null;
    }

    @Override
    public List<Contact> findContactsByUser(User user) {
        return user.getContacts();
    }

    @Override
    // @Transactional
    public Contact saveContact(Contact contact) {
        List<Contact> contacts = phoneBook.getContacts();
        Contact persistedContact = null;

        int ind = contacts.indexOf(contact);

        if (ind != -1) {
            persistedContact = contacts.get(ind);
            persistedContact.initialize(contact);
        } else {
            persistedContact = new Contact();

            // Dealing with ID
            Long contactNextId = phoneBook.getIdTrace().getContactNextId();
            persistedContact.setContactId(contactNextId);
            phoneBook.getIdTrace().setRoleNextId(++contactNextId);

            persistedContact.initialize(contact);
            contacts.add(persistedContact);
        }

        // backlinking
/*        User contactUser = persistedContact.getUser();
        if (!contactUser.getContacts().contains(persistedContact)) {
            contactUser.getContacts().add(persistedContact);
        }*/

        List<Contact> persistedContactUserContacts = persistedContact.getUser().getContacts();
        if (!persistedContactUserContacts.contains(persistedContact)) {
            persistedContactUserContacts.add(persistedContact);
        }

        xmlManager.marshal();

        return persistedContact;
    }

    @Override
    // @Transactional
    public void delete(Long id) {
        delete(findOne(id));
    }

    @Override
    // @Transactional
    public void delete(Contact contact) {
        phoneBook.getContacts().remove(contact);

        // backlinking
        contact.getUser().getContacts().remove(contact);

        xmlManager.marshal();
    }

    // == SEARCH BEGIN ==

    // Search by User
    @Override
    public List<Contact> findByUserAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(User user, String firstNamePattern) {
        List<Contact> resultList = new ArrayList<>();

        for (Contact contact : user.getContacts()) {
            if (contact.getFirstName().toLowerCase().contains(firstNamePattern.toLowerCase())) {
                resultList.add(contact);
            }
        }

        resultList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getFirstName().compareToIgnoreCase(contact2.getFirstName());
            }
        });

        return resultList;
    }

    @Override
    public List<Contact> findByUserAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(User user, String lastNamePattern) {
        List<Contact> resultList = new ArrayList<>();

        for (Contact contact : user.getContacts()) {
            if (contact.getLastName().toLowerCase().contains(lastNamePattern.toLowerCase())) {
                resultList.add(contact);
            }
        }

        resultList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getLastName().compareToIgnoreCase(contact2.getLastName());
            }
        });

        return resultList;
    }

    @Override
    public List<Contact> findByUserAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(User user, String patronymicPattern) {
        List<Contact> resultList = new ArrayList<>();

        for (Contact contact : user.getContacts()) {
            if (contact.getPatronymic().toLowerCase().contains(patronymicPattern.toLowerCase())) {
                resultList.add(contact);
            }
        }

        resultList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getPatronymic().compareToIgnoreCase(contact2.getPatronymic());
            }
        });

        return resultList;
    }

    @Override
    public List<Contact> findByUserAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(User user, String cellPhonePattern) {
        List<Contact> resultList = new ArrayList<>();

        for (Contact contact : user.getContacts()) {
            if (contact.getCellPhone().toLowerCase().contains(cellPhonePattern.toLowerCase())) {
                resultList.add(contact);
            }
        }

        resultList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getCellPhone().compareToIgnoreCase(contact2.getCellPhone());
            }
        });

        return resultList;
    }

    @Override
    public List<Contact> findByUserAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(User user, String homePhonePattern) {
        List<Contact> resultList = new ArrayList<>();

        for (Contact contact : user.getContacts()) {
            if (contact.getHomePhone().toLowerCase().contains(homePhonePattern.toLowerCase())) {
                resultList.add(contact);
            }
        }

        resultList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getHomePhone().compareToIgnoreCase(contact2.getHomePhone());
            }
        });

        return resultList;
    }

    @Override
    public List<Contact> findByUserAndAddressContainingIgnoreCaseOrderByFirstNameAsc(User user, String addressPattern) {
        List<Contact> resultList = new ArrayList<>();

        for (Contact contact : user.getContacts()) {
            if (contact.getAddress().toLowerCase().contains(addressPattern.toLowerCase())) {
                resultList.add(contact);
            }
        }

        resultList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getAddress().compareToIgnoreCase(contact2.getAddress());
            }
        });

        return resultList;
    }

    @Override
    public List<Contact> findByUserAndEmailContainingIgnoreCaseOrderByFirstNameAsc(User user, String emailPattern) {
        List<Contact> resultList = new ArrayList<>();

        for (Contact contact : user.getContacts()) {
            if (contact.getEmail().toLowerCase().contains(emailPattern.toLowerCase())) {
                resultList.add(contact);
            }
        }

        resultList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getEmail().compareToIgnoreCase(contact2.getEmail());
            }
        });

        return resultList;
    }

    // Search by UserId
    @Override
    public List<Contact> findByUserUserIdAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String firstNamePattern) {
        return findByUserAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(userIDAO.findOne(userId), firstNamePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String lastNamePattern) {
        return findByUserAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(userIDAO.findOne(userId), lastNamePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String patronymicPattern) {
        return findByUserAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(userIDAO.findOne(userId), patronymicPattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String cellPhonePattern) {
        return findByUserAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(userIDAO.findOne(userId), cellPhonePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String homePhonePattern) {
        return findByUserAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(userIDAO.findOne(userId), homePhonePattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndAddressContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String addressPattern) {
        return findByUserAndAddressContainingIgnoreCaseOrderByFirstNameAsc(userIDAO.findOne(userId), addressPattern);
    }

    @Override
    public List<Contact> findByUserUserIdAndEmailContainingIgnoreCaseOrderByFirstNameAsc(Long userId, String emailPattern) {
        return findByUserAndEmailContainingIgnoreCaseOrderByFirstNameAsc(userIDAO.findOne(userId), emailPattern);
    }

    // == SEARCH END ==
}
