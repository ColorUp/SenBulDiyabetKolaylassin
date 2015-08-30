package com.mycodeyourproject.senbuldiyabetkolaylassin;

/**
 * Created by Burak on 26.08.2015.
 */
public class Extensions
{
    public static String Format(String format,String args0)
    {
        if (format.contains("{0}"))
            format = format.replace("{0}", args0.toString());
        return format;
    }

    public static String Format(String format,String args0, String args1)
    {
        if (format.contains("{0}"))
            format = format.replace("{0}", args0.toString());
        if (format.contains("{1}"))
            format = format.replace("{1}", args1.toString());
        return format;
    }

    public static String Format(String format,String args0, String args1, String args2)
    {
        if (format.contains("{0}"))
            format = format.replace("{0}", args0.toString());
        if (format.contains("{1}"))
            format = format.replace("{1}", args1.toString());
        if (format.contains("{2}"))
            format = format.replace("{2}", args2.toString());
        return format;
    }
}
