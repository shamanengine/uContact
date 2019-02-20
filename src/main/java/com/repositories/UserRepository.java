package com.repositories;

import com.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 04.11.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {


/*    @Autowired
    public UserRepository(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }*/

    @Override
    List<User> findAll();

    @Override
    User findOne(Long id);

    User findUserByLogin(String login);

    @Override
    <SubUser extends User> SubUser saveAndFlush(SubUser subUser);

}
