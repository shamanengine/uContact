package com.dao.implementations.xml;

import com.dao.entities.Role;
import com.dao.implementations.xml.helpers.PhoneBook;
import com.dao.implementations.xml.helpers.XmlManager;
import com.dao.interfaces.RoleIDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 23.11.2016.
 */
@Repository
public class RoleDAO implements RoleIDAO<Role> {
    @Autowired
    PhoneBook phoneBook;
    @Autowired
    XmlManager xmlManager;

    /*
    PhoneBook phoneBook = xmlManager.getPhoneBook();
    XmlManager xmlManager = new XmlManager();
    */

    @Override
    public List<Role> findAll() {
        return phoneBook.getRoles();
    }

    @Override
    public Role findOne(Long id) {
        List<Role> roles = phoneBook.getRoles();
        for (Role role : roles) {
            if (role.getRoleId().equals(id))
                return role;
        }
        return null;
    }

    @Override
    // @Transactional
    public Role saveRole(Role role) {
        List<Role> roles = phoneBook.getRoles();
        Role persistedRole = null;

        int ind = roles.indexOf(role);

        if (ind != -1) {
            persistedRole = roles.get(ind);
            persistedRole.initialize(role);
        } else {
            persistedRole = new Role();

            Long roleNextId = phoneBook.getIdTrace().getRoleNextId();
            persistedRole.setRoleId(roleNextId);
            phoneBook.getIdTrace().setRoleNextId(++roleNextId);

            persistedRole.initialize(role);
            roles.add(persistedRole);
        }

        xmlManager.marshal();

        return persistedRole;
    }
}
