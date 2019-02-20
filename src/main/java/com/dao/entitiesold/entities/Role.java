package com.dao.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 04.11.2016.
 */
@Entity
@XmlRootElement(name = "role")
public class Role {
    // Fields
    public static final Long ADMIN_ROLE_ID = 1L;
    public static final Long USER_ROLE_ID = 2L;
    public static final Long MANAGER_ROLE_ID = 3L;

    private Long roleId;
    private String title;
    private String description;
    // Mapped
    private List<User> userList = new ArrayList<>();

    // Getters & Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    @XmlElement(name = "roleId")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "TITLE")
    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "role")
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    // Constructors
    public Role() {
    }

    public Role(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // equals(); hashCode(); toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return !(roleId != null ? !roleId.equals(role.roleId) : role.roleId != null);
    }

    @Override
    public int hashCode() {
        return roleId != null ? roleId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + roleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
