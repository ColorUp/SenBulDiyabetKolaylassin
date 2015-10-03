package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Burak on 26.08.2015.
 */
public class DataTransferObjects {
    public static class User {
        public long id = 0;
        public String username = "";
        public String password = "";
        public Date dateOfBirth = new Date();
        public int gender;
        public String turkishId = "";
        public String name = "";
        public String surname = "";

        Map<String, String> getUserObject() {
            Map<String, String> user = DatabaseQuery.GetParameterList("USER");
            if (id == 0)
                user.put("ID", "NULL");
            else
                user.put("ID", String.valueOf(id));

            user.put("USERNAME", username.trim());
            user.put("PASSWORD", password.trim());

            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String date = df.format(dateOfBirth);
            user.put("BIRTHOFDATE", "1987-03-03");

            user.put("GENDER", String.valueOf(gender));
            user.put("TURKISHID", turkishId.trim());
            user.put("NAME", name);
            user.put("SURNAME", surname);

            return user;
        }

        User(String usernameInput, String passwordInput, Date dateOfBirthInput, int genderInput, String turkishIdInput,
             String nameInput, String surnameInput) {
            username = usernameInput;
            password = passwordInput;
            dateOfBirth = dateOfBirthInput;
            gender = genderInput;
            turkishId = turkishIdInput;
            name = nameInput;
            surname = surnameInput;
        }
    }
}