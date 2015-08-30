package com.mycodeyourproject.senbuldiyabetkolaylassin;

/**
 * Created by Burak on 26.08.2015.
 */
public class Enums
{
    public enum PhpSqlOperation
    {
        TABLE("TABLE"), INSERT("INSERT"), UPDATE("UPDATE"), SELECT("SELECT"), DELETE("DELETE");

        private String statusCode;

        private PhpSqlOperation(String s)
        {
            statusCode = s;
        }

        public String getStatusCode()
        {
            return statusCode;
        }
    }
}
