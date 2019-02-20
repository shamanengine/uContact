package com.dao.entitiesold2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author Raccoon
 * @version 1.0, 15.06.2016.
 */
public class KickStarter {
    public static void main(String[] args) {
        // Users users = new Users();
//        Users2 users = new Users2();

        PhoneBook phoneBook = PhoneBook.getInstance();

        User user1 = new User("l1", "p1", "f1", "l1", "p1");
        user1.setUserId(User.nextId++);

        Role userRole = new Role("USER", "user description");
        userRole.setRoleId(Role.USER_ROLE_ID);

        user1.setRole(userRole);
        userRole.getUserList().add(user1);

        User user2 = new User("l2", "p2", "f2", "l2", "p2");
        user2.setUserId(User.nextId++);

        user2.setRole(userRole);
        userRole.getUserList().add(user2);

        /*users.add(user1);
        users.add(user2);*/

        /*users.getUsersMap().put(user1.getUserId(), user1);
        users.getUsersMap().put(user2.getUserId(), user2);


        System.out.println(users);*/

        phoneBook.getRoles().add(userRole);
        phoneBook.getUsers().add(user1);
        phoneBook.getUsers().add(user2);
        /*phoneBook.getContacts().add();*/
        try {
            File phoneBookFile = new File(
                    "D:\\Java\\jdkMy\\Lardi\\uContact5\\src\\main\\java\\com\\dao\\entities\\phonebook.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(PhoneBook.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // write to file
            marshaller.marshal(phoneBook, phoneBookFile);
            marshaller.marshal(phoneBook, System.out);

            // read from file

            // users = (Users) unmarshaller.unmarshal(usersFile);
            phoneBook = (PhoneBook) unmarshaller.unmarshal(phoneBookFile);
            System.out.println(phoneBook);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
