package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Burak on 26.08.2015.
 */
public class DataTransferObjects
{
    public static class User
    {
        long id=0;
        String username="";
        String password="";
        Date dateOfBirth=new Date();
        int gender=0;
        String turkishId="";
        String name="";
        String surname="";

        Map<String,String> getUserObject()
        {
            Map<String,String> user = DatabaseQuery.GetParameterList("USER");
            user.put("ID", "NULL");
            user.put("USERNAME", username);
            user.put("PASSWORD", password);

            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String date = df.format(dateOfBirth);
            user.put("BIRTHOFDATE", "1987-03-03");

            user.put("GENDER", String.valueOf(gender));
            user.put("TURKISHID", turkishId);
            user.put("NAME", name);
            user.put("SURNAME", surname);

            return user;
        }

        User(String usernameInput,String passwordInput, Date dateOfBirthInput, int genderInput, String turkishIdInput,
                  String nameInput, String surnameInput)
        {
            username=usernameInput;
            password=passwordInput;
            dateOfBirth=dateOfBirthInput;
            gender=genderInput;
            turkishId=turkishIdInput;
            name=nameInput;
            surname=surnameInput;
        }
    }
}
