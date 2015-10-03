package com.mycodeyourproject.senbuldiyabetkolaylassin;

/**
 * Created by Burak on 26.08.2015.
 */
public class Enums {
    public enum PhpSqlOperation {
        TABLE("TABLE"), INSERT("INSERT"), UPDATE("UPDATE"), SELECT("SELECT"), DELETE("DELETE");

        private String statusCode;

        private PhpSqlOperation(String s) {
            statusCode = s;
        }

        public String getStatusCode() {
            return statusCode;
        }
    }

    public static int GetGenderNumber(String value)
    {
        if (value.matches("Erkek"))
            return  1;
        else if (value.matches("Kadın"))
            return 2;
        else
            return 0;
    }

    public static String GetGenderString(int value)
    {
        if (value == 1)
            return  "Erkek";
        else if (value == 2)
            return "Kadın";
        else
            return null;
    }
}
