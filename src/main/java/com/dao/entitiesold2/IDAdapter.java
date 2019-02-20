package com.dao.entitiesold2;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author A.Tymchenko
 * @version 1.0, 21.11.2016.
 */
public class IDAdapter extends XmlAdapter<String, Long> {
    @Override
    public Long unmarshal(String string) throws Exception {
        return DatatypeConverter.parseLong(string);
    }

    @Override
    public String marshal(Long value) throws Exception {
        return DatatypeConverter.printLong(value);
    }
}
