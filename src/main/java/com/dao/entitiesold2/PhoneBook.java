package com.dao.entitiesold2;

import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
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

    /*private static PhoneBook ourInstance = new PhoneBook();

    public static PhoneBook getInstance() {
        return ourInstance;
    }
    */

    private static volatile PhoneBook instance;

/*    public static PhoneBook getInstance() {
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
    }*/

    public static PhoneBook getInstance(){
        if(instance == null){
            instance = new PhoneBook();
        }
        return instance;
    }

    // JAXB infrastructure
    @XmlTransient
    private File phoneBookFile;
    @XmlTransient
    private JAXBContext jaxbContext;
    @XmlTransient
    private Marshaller marshaller;
    @XmlTransient
    private Unmarshaller unmarshaller;

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

    private PhoneBook() {
        idTrace = new IdTrace();
        // idTrace.setRoleNextId(idTrace.getRoleNextId()+1);
        roles = new ArrayList<>();
        users = new ArrayList<>();
        contacts = new ArrayList<>();

        phoneBookFile = new File(
                "D:\\Java\\jdkMy\\Lardi\\uContact5\\src\\main\\java\\com\\dao\\entities\\phonebook.xml");

        try {
            jaxbContext = JAXBContext.newInstance(PhoneBook.class);
            marshaller = jaxbContext.createMarshaller();
            unmarshaller = jaxbContext.createUnmarshaller();
            // formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // read from file
            if (phoneBookFile.length() > 0) {
                instance = (PhoneBook) unmarshaller.unmarshal(phoneBookFile);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // JAXB infrastructure
    public File getPhoneBookFile() {
        return phoneBookFile;
    }

    public void setPhoneBookFile(File phoneBookFile) {
        this.phoneBookFile = phoneBookFile;
    }

    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public void setJaxbContext(JAXBContext jaxbContext) {
        this.jaxbContext = jaxbContext;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

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

    // methods
    public void marshal() {
        try {
            marshaller.marshal(this, phoneBookFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
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
