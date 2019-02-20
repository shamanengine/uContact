package com.dao.entitiesold2;

import com.dao.implementations.xml.RoleDAO;
import com.dao.implementations.xml.UserDAO;
import com.dao.interfaces.RoleIDAO;
import com.dao.interfaces.UserIDAO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author A.Tymchenko
 * @version 1.0, 22.11.2016.
 */
@Service
@ComponentScan(basePackages = "com")
@EnableTransactionManagement
public class test1 {

    /*@Autowired
    private */

    /*private static UserIDAO userDAO = new UserDAO();*/

    public static void main(String[] args) {
        RoleIDAO roleIDAO = new RoleDAO();
        UserIDAO userIDAO = new UserDAO();

        Role adminRole = new Role("ADMIN", "admin description");
        adminRole.setRoleId(Role.ADMIN_ROLE_ID);
        roleIDAO.saveRole(adminRole);

        Role userRole = new Role("USER", "user description");
        userRole.setRoleId(Role.USER_ROLE_ID);
        roleIDAO.saveRole(userRole);

        User user1 = new User("l1", "p1", "f1", "l1", "p1");
        user1 = userIDAO.saveUser(user1);

        User user2 = new User("l2", "p2", "f2", "l2", "p2");
        user2 = userIDAO.saveUser(user2);

        User user3 = userIDAO.findUserByLogin("l1");
        user3.setLogin("newl1");
        userIDAO.saveUser(user3);

        User user4 = new User("l4", "p4", "f4", "l4", "p4");
        user4.setRole(adminRole);
        userIDAO.saveUser(user4);

        System.out.println(userIDAO.findAll());
    }


}
