package com.dao.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author A.Tymchenko
 * @version 1.0, 21.11.2016.
 */
@XmlRootElement
public class Users2 {

    // @XmlElement(name = "user")
    private Map<Long, User> usersMap = new HashMap<>();

    /*@XmlElementWrapper(name="uM")*/
    public Map<Long, User> getUsersMap() {
        return usersMap;
    }

    public void setUsersMap(Map<Long, User> usersMap) {
        this.usersMap = usersMap;
    }
}
