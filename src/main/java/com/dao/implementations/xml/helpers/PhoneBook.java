package com.dao.implementations.xml.helpers;

import com.dao.entities.Contact;
import com.dao.entities.Role;
import com.dao.entities.User;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 21.11.2016.
 */
// Singleton
@Repository

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneBook {

/*    private static volatile PhoneBook instance;

    public static PhoneBook getInstance() {
        PhoneBook localInstance = instance;
        if (localInstance == null) {
            synchronized (PhoneBook.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PhoneBook();
                }
            }
        }
        return localInstance;
    }

    public static PhoneBook getInstance(){
        if(instance == null){
            instance = new PhoneBook();
        }
        return instance;
    }*/


    // List with IDs
    @XmlElement(name = "idTrace")
    private IdTrace idTrace;

    // Role - User - Contact
    @XmlElement(name = "role")
    private List<Role> roles;

    @XmlElement(name = "user")
    private List<User> users;

    @XmlElement(name = "contact")
    private List<Contact> contacts;

    // List with IDs
    public IdTrace getIdTrace() {
        return idTrace;
    }

    public void setIdTrace(IdTrace idTrace) {
        this.idTrace = idTrace;
    }

    // Role - User - Contact
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    // Constructors
    public PhoneBook() {
        idTrace = new IdTrace();
        roles = new ArrayList<>();
        users = new ArrayList<>();
        contacts = new ArrayList<>();
    }

    // toString();
    @Override
    public String toString() {
        return "PhoneBook{" +
                "roles=" + roles +
                ", users=" + users +
                ", contacts=" + contacts +
                '}';
    }

}
