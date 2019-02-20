package com.dao.entities;

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
        Users2 users = new Users2();

        User user1 = new User("l1", "p1", "f1", "l1", "p1");
        user1.setUserId(User.nextId++);
        user1.setRoleId(1L);

        User user2 = new User("l2", "p2", "f2", "l2", "p2");
        user2.setUserId(User.nextId++);
        user2.setRoleId(2L);

        /*users.add(user1);
        users.add(user2);*/

        users.getUsersMap().put(user1.getUserId(), user1);
        users.getUsersMap().put(user2.getUserId(), user2);


        System.out.println(users);
        try {
            File usersFile = new File("D:\\Java\\jdkMy\\Lardi\\uContact5\\src\\main\\java\\com\\dao\\entities\\users.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Users2.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // write to file
            marshaller.marshal(users, usersFile);
            marshaller.marshal(users, System.out);

            // read from file
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // users = (Users) unmarshaller.unmarshal(usersFile);
            users = (Users2) unmarshaller.unmarshal(usersFile);
            System.out.println(users);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
