package com.dao.interfaces;

import com.dao.entities.Role;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 05.11.2016.
 */
public interface RoleIDAO<S extends Role> {
    List<S> findAll();

    S findOne(Long id);

    S saveRole(Role role);
}
