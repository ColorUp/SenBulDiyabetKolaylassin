package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Burak on 30.09.2015.
 */
public class Converter {
    public static Date StringToDate(String string) {
        Date birthDate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            birthDate = format.parse(string.toString());
        } catch (ParseException e) {
            return null;
        }

        return birthDate;
    }

    public static String BooleanToString(Boolean bool) {
        String boolStr = "";
        boolStr = bool == true ? boolStr = "1" : "0";

        return boolStr;
    }

    public static String DateToString(Date date) {
        String dateStr = "";
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        dateStr = df.format(date);

        return dateStr;
    }

    public static String TimeToString(Date date) {
        String timeStr = "";
        DateFormat tf = new SimpleDateFormat("HH:mm");
        timeStr = tf.format(date);

        return timeStr;
    }

    public static String DateTimeToString(Date dateTime) {
        String dateTimeStr = "";
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateTimeStr = df.format(dateTime);

        return dateTimeStr;
    }
}
