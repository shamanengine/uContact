package com.dao.implementations.xml.helpers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

/**
 * @author Raccoon
 * @version 1.0, 05.07.2016.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    public LocalDateTime unmarshal(String v) throws Exception {
        if (v == null)
            return LocalDateTime.now();
        else
            return LocalDateTime.parse(v);
    }

    public String marshal(LocalDateTime v) throws Exception {
        if (v == null)
            v = LocalDateTime.now();
        return v.toString();
    }
}
