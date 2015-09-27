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

    public static Gender SetGender(String string)
    {
        return  Gender.valueOf(string);
    }

    public enum Gender {
        NONE(""), MALE("Erkek"), FEMALE("Kadın");
        private int value;

        private Gender(String value) {
            if (value == "Erkek")
                this.value = 1;
            else if (value == "Kadın")
                this.value = 2;
            else
                this.value = 0;
        }

        public Integer getStatusCode() {
            return value;
        }
    }
}
