package com.dao.entities;

import com.dao.implementations.xml.helpers.IDAdapter;
import com.dao.implementations.xml.helpers.LocalDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 04.11.2016.
 */
@Entity

/*@XmlRootElement*/
public class User {
    public static Long nextId = 0L;

    // Fields
    private Long userId;
    private String login;
    private String password;
    // 1
    // private Timestamp createTime;
    private LocalDateTime createTime;
    private String firstName;
    private String lastName;
    private String patronymic;
    // mapped
    private Role role;
    private List<Contact> contacts = new ArrayList<>();
    // xml
    /*private Long roleId;*/

    // Getters & Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    // @XmlElement(name = "userId")

    @XmlID
    @XmlJavaTypeAdapter(IDAdapter.class)
    @XmlAttribute
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "LOGIN")

    @XmlAttribute
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "PASSWORD")

    @XmlAttribute
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "CREATE_TIME")

    @XmlAttribute
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "FIRST_NAME")

    @XmlAttribute
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME")

    @XmlAttribute
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "PATRONYMIC")

    @XmlAttribute
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")

    @XmlIDREF
    @XmlAttribute
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user")

    @XmlIDREF
    @XmlList
    @XmlElement
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /*@XmlElement(name = "roleId")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }*/

    // initializers
    public void initialize(User user) {
        login = user.getLogin();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        patronymic = user.getPatronymic();
        role = user.getRole();
        contacts = user.getContacts();
    }

    // Constructors
    public User() {
    }

    public User(String login, String password, String firstName, String lastName, String patronymic) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    // equals(); hashCode(); toString();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(userId != null ? !userId.equals(user.userId) : user.userId != null);
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "Id=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
