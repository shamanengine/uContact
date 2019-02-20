package com.dao.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 18.11.2016.
 */
@XmlRootElement(name = "contacts")
public class Contacts {

    @XmlElement(name = "contact")
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public String toString() {
        return Arrays.deepToString(contacts.toArray());
    }
}
