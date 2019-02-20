package com.dao.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author A.Tymchenko
 * @version 1.0, 04.11.2016.
 */
@Entity
@XmlRootElement(name = "contact")
public class Contact {
    // Fields
    private Long contactId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String cellPhone;
    private String homePhone;
    private String address;
    private String email;
    // mapped
    private User user;
    // xml
    private Long userId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_ID")
    @XmlElement(name = "contactId")
    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "FIRST_NAME")
    @XmlElement(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME")
    @XmlElement(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "PATRONYMIC")
    @XmlElement(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "CELL_PHONE")
    @XmlElement(name = "cellPhone")
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Basic
    @Column(name = "HOME_PHONE")
    @XmlElement(name = "homePhone")
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Basic
    @Column(name = "ADDRESS")
    @XmlElement(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "EMAIL")
    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Special getters & setters
    public void setContact(Contact contact) {
        this.firstName = contact.firstName;
        this.lastName = contact.lastName;
        this.patronymic = contact.patronymic;
        this.cellPhone = contact.cellPhone;
        this.homePhone = contact.homePhone;
        this.address = contact.address;
        this.email = contact.email;
    }

    @XmlElement(name = "userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Constructors
    public Contact() {

    }

    public Contact(String firstName, String lastName, String patronymic, String cellPhone,
                   String homePhone, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.address = address;
        this.email = email;
    }

    // equals & hashCode & toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return !(contactId != null ? !contactId.equals(contact.contactId) : contact.contactId != null);
    }

    @Override
    public int hashCode() {
        return contactId != null ? contactId.hashCode() : 0;
    }

    @Override
    public String toString() {
      /*  return "";*/
        return "Contact{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
