package com.dao.entitiesold2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author A.Tymchenko
 * @version 1.0, 18.11.2016.
 */
@XmlRootElement(name = "users")
public class Users {
    @XmlElement(name = "user")
    private List<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return Arrays.deepToString(users.toArray());
    }

    public void add(User user) {
        users.add(user);
    }
}
