package com.dao.implementations.xml.helpers;

import com.dao.interfaces.RoleIDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author A.Tymchenko
 * @version 1.0, 23.11.2016.
 */
@Component
public class XmlManager {

    private File phoneBookFile;
    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Autowired
    private PhoneBook phoneBook;

    @Autowired
    RoleIDAO roleIDAO;

    public XmlManager() {
        /*phoneBookFile = new File(
                "D:\\Java\\jdkMy\\Lardi\\uContact5\\src\\main\\java\\com\\dao\\entities\\phonebook.xml");*/
        phoneBookFile = new File("D:\\Java\\jdkMy\\Lardi\\uContact5\\src\\main\\resources\\phonebook.xml");
        try {
            jaxbContext = JAXBContext.newInstance(PhoneBook.class);
            marshaller = jaxbContext.createMarshaller();
            unmarshaller = jaxbContext.createUnmarshaller();
            // formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // read from file
            if (phoneBookFile.length() > 0) {
                phoneBook = (PhoneBook) unmarshaller.unmarshal(phoneBookFile);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

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

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    // methods
    public void marshal() {
        try {
            marshaller.marshal(phoneBook, phoneBookFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /*public void initialize() {
        if (phoneBookFile.length() <= 0) {
            Role adminRole = new Role("ADMIN", "admin description");
            adminRole.setRoleId(Role.ADMIN_ROLE_ID);
            roleIDAO.saveRole(adminRole);

            Role userRole = new Role("USER", "user description");
            userRole.setRoleId(Role.USER_ROLE_ID);
            roleIDAO.saveRole(userRole);
        }
    }*/
}
