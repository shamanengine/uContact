package com.dao.implementations.xml.helpers;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author A.Tymchenko
 * @version 1.0, 22.11.2016.
 */
public class IdTrace {

    private Long roleNextId;
    private Long userNextId;
    private Long contactNextId;

    public IdTrace() {
        roleNextId = 1L;
        userNextId = 1L;
        contactNextId = 1L;
    }

    public IdTrace(Long roleNextId, Long userNextId, Long contactNextId) {
        this.roleNextId = roleNextId;
        this.userNextId = userNextId;
        this.contactNextId = contactNextId;
    }

    @XmlAttribute
    public Long getRoleNextId() {
        return roleNextId;
    }

    public void setRoleNextId(Long roleNextId) {
        this.roleNextId = roleNextId;
    }

    @XmlAttribute
    public Long getUserNextId() {
        return userNextId;
    }

    public void setUserNextId(Long userNextId) {
        this.userNextId = userNextId;
    }

    @XmlAttribute
    public Long getContactNextId() {
        return contactNextId;
    }

    public void setContactNextId(Long contactNextId) {
        this.contactNextId = contactNextId;
    }
}
