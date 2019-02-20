package com.repositories;

import com.dao.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 04.11.2016.
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    // List<Role> findAccountsByBalanceGreaterThanEqual(BigDecimal amount);
    @Override
    Role getOne(Long id);

    @Override
    List<Role> findAll();

    @Override
    Role findOne(Long id);
}
