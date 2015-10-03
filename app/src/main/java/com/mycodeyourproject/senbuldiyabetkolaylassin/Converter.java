package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Burak on 30.09.2015.
 */
public class Converter {
    public static Date StringToDate(String string)
    {
        Date birthDate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            birthDate = format.parse(string.toString());
        } catch (ParseException e) {
            return null;
        }

        return birthDate;
    }
}
