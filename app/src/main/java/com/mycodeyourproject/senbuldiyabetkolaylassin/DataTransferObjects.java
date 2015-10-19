package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        public Date recorddate = new Date();

        Map<String, String> getUserObject() {
            Map<String, String> user = DatabaseQuery.GetParameterList("USER");
            if (id == 0)
                user.put("ID", "NULL");
            else
                user.put("ID", String.valueOf(id));

            user.put("USERNAME", username.trim());
            user.put("PASSWORD", password.trim());
            user.put("BIRTHOFDATE", Converter.DateToString(dateOfBirth));
            user.put("GENDER", String.valueOf(gender));
            user.put("TURKISHID", turkishId.trim());
            user.put("NAME", name);
            user.put("SURNAME", surname);
            user.put("RECORDDATE", Converter.DateTimeToString(recorddate));

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

        public static boolean UserNameIsValid(String userName) {
            if (userName.contentEquals("") || userName == null)
                return false;

            Map<String, String> user = DatabaseQuery.GetParameterList("USER");
            if (userName == null)
                user.remove("USERNAME");
            else
                user.put("USERNAME", userName.trim());

            user.remove("ID");
            user.remove("PASSWORD");
            user.remove("BIRTHOFDATE");
            user.remove("GENDER");
            user.remove("TURKISHID");
            user.remove("NAME");
            user.remove("SURNAME");
            user.remove("RECORDDATE");

            List<Map<Object, Object>> userList = DatabaseQuery.Select("USER", user);
            Map<Object, Object> userValues = null;
            if (userList != null) {
                userValues = userList.get(0);
                if (userValues.get("USERNAME").toString().matches(userName)) {
                    return true;
                }
            }
            return false;
        }

        public static boolean UserIsValid(String userName, String password) {
            if (userName.contentEquals("") || userName == null)
                return false;

            Map<String, String> user = DatabaseQuery.GetParameterList("USER");

            if (userName == null)
                user.remove("USERNAME");
            else
                user.put("USERNAME", userName.trim());

            user.remove("ID");
            user.remove("PASSWORD");
            user.remove("BIRTHOFDATE");
            user.remove("GENDER");
            user.remove("TURKISHID");
            user.remove("NAME");
            user.remove("SURNAME");
            user.remove("RECORDDATE");

            List<Map<Object, Object>> userList = DatabaseQuery.Select("USER", user);
            Map<Object, Object> userValues = null;
            if (userList != null) {
                userValues = userList.get(0);
                if (userValues.get("PASSWORD").toString().matches(password)) {
                    return true;
                }

            }

            return false;
        }
    }

    public static class UserLifeStyle {
        public long id = 0;
        public String userName = "";
        public Float height = 0f;
        public Float weight = 0f;
        public byte profession = 0;
        public byte maritalStatus = 0;
        public Date wakeUpTime = new Date();
        public Date sleepTime = new Date();
        public boolean hasSportActivity = false;
        public byte sportSmartness = 0;
        public Date sportTime = new Date();
        public boolean snackAfterBreakfast = false;
        public Date breakfastTime = new Date();
        public boolean snackAfterLunch = false;
        public Date lunchTime = new Date();
        public boolean snackAfterDinner = false;
        public Date dinnerTime = new Date();
        public boolean hasJourney = false;
        public Float dailyWaterDrinks = 0f;
        public boolean isSmoking = false;
        public boolean hasAllergic = false;
        public boolean useMedicine = false;
        public String information = "";
        public boolean hasAlcohol=false;

        Map<String, String> getUserLifeStyleObject() {
            Map<String, String> userLifeStyle = DatabaseQuery.GetParameterList("USERLIFESTYLE");
            if (id == 0)
                userLifeStyle.put("ID", "NULL");
            else
                userLifeStyle.put("ID", String.valueOf(id));

            userLifeStyle.put("USERNAME", userName.trim());
            userLifeStyle.put("HEIGHT", String.valueOf(height));
            userLifeStyle.put("WEIGHT", String.valueOf(weight));
            userLifeStyle.put("PROFESSION", String.valueOf(profession));
            userLifeStyle.put("MARITALSTATUS", String.valueOf(maritalStatus));
            userLifeStyle.put("WAKEUPTIME", Converter.TimeToString(wakeUpTime));
            userLifeStyle.put("SLEEPTIME", Converter.TimeToString(sleepTime));
            userLifeStyle.put("HASSPORTACTIVITY", Converter.BooleanToString(hasSportActivity));
            userLifeStyle.put("SPORTSMARTNESS", String.valueOf(sportSmartness));
            userLifeStyle.put("SPORTTIME", Converter.TimeToString(sportTime));
            userLifeStyle.put("HASJOURNEY", Converter.BooleanToString(hasJourney));
            userLifeStyle.put("BREAKFASTTIME", Converter.TimeToString(breakfastTime));
            userLifeStyle.put("SNACKAFTERBREAKFAST", Converter.BooleanToString(snackAfterBreakfast));
            userLifeStyle.put("LUNCHTIME", Converter.TimeToString(lunchTime));
            userLifeStyle.put("SNACKAFTERLUNCH", Converter.BooleanToString(snackAfterLunch));
            userLifeStyle.put("DINNERTIME", Converter.TimeToString(dinnerTime));
            userLifeStyle.put("SNACKAFTERDINNER", Converter.BooleanToString(snackAfterDinner));
            userLifeStyle.put("DAILYWATERDRINKS", String.valueOf(dailyWaterDrinks));
            userLifeStyle.put("ISSMOKING", Converter.BooleanToString(isSmoking));
            userLifeStyle.put("HASALLERGIC", Converter.BooleanToString(hasAllergic));
            userLifeStyle.put("USEMEDICINE", Converter.BooleanToString(useMedicine));
            userLifeStyle.put("INFORMATION", information);
            userLifeStyle.put("HASALCOHOL", Converter.BooleanToString(hasAlcohol));

            return userLifeStyle;
        }

        UserLifeStyle(String usernameInput, Float heightInput, Float weightInput, Byte professionInput,
                      Byte maritalStatusInput, Date wakeUpTimeInput, Date sleepTimeInput, boolean hasSportActivityInput,
                      Byte sportSmartnessInput, Date sportTimeInput, boolean snackAfterBreakfastInput,
                      Date breakfastTimeInput, boolean snackAfterLunchInput, Date lunchTimeInput, boolean snackAfterDinnerInput,
                      Date dinnerTimeInput, boolean hasJourneyInput, Float dailyWaterDrinksInput, boolean isSmokingInput,
                      boolean hasAllergicInput, boolean useMedicineInput, String informationInput, boolean hasAlcoholInput) {

            userName = usernameInput;
            height = heightInput;
            weight = weightInput;
            profession = professionInput;
            maritalStatus = maritalStatusInput;
            wakeUpTime = wakeUpTimeInput;
            sleepTime = sleepTimeInput;
            hasSportActivity = hasSportActivityInput;
            sportSmartness = sportSmartnessInput;
            sportTime = sportTimeInput;
            snackAfterBreakfast = snackAfterBreakfastInput;
            breakfastTime = breakfastTimeInput;
            snackAfterLunch = snackAfterLunchInput;
            lunchTime = lunchTimeInput;
            snackAfterDinner = snackAfterDinnerInput;
            dinnerTime = dinnerTimeInput;
            hasJourney = hasJourneyInput;
            dailyWaterDrinks = dailyWaterDrinksInput;
            isSmoking = isSmokingInput;
            hasAllergic = hasAllergicInput;
            useMedicine = useMedicineInput;
            information = informationInput;
            hasAlcohol=hasAlcoholInput;
        }

        public static List<Map<Object, Object>> getUserLifeStyleList(String userName) {
            if (userName.contentEquals("") || userName == null)
                return null;

            Map<String, String> userLifeStyle = DatabaseQuery.GetParameterList("USERLIFESTYLE");
            if (userName == null)
                userLifeStyle.remove("USERNAME");
            else
                userLifeStyle.put("USERNAME", userName.trim());

            userLifeStyle.remove("ID");
            userLifeStyle.remove("HEIGHT");
            userLifeStyle.remove("WEIGHT");
            userLifeStyle.remove("PROFESSION");
            userLifeStyle.remove("MARITALSTATUS");
            userLifeStyle.remove("WAKEUPTIME");
            userLifeStyle.remove("SLEEPTIME");
            userLifeStyle.remove("HASSPORTACTIVITY");
            userLifeStyle.remove("SPORTSMARTNESS");
            userLifeStyle.remove("SPORTTIME");
            userLifeStyle.remove("HASJOURNEY");
            userLifeStyle.remove("BREAKFASTTIME");
            userLifeStyle.remove("SNACKAFTERBREAKFAST");
            userLifeStyle.remove("LUNCHTIME");
            userLifeStyle.remove("SNACKAFTERLUNCH");
            userLifeStyle.remove("DINNERTIME");
            userLifeStyle.remove("SNACKAFTERDINNER");
            userLifeStyle.remove("DAILYWATERDRINKS");
            userLifeStyle.remove("ISSMOKING");
            userLifeStyle.remove("HASALLERGIC");
            userLifeStyle.remove("USEMEDICINE");
            userLifeStyle.remove("INFORMATION");
            userLifeStyle.remove("HASALCOHOL");

            List<Map<Object, Object>> userlifestyleList = DatabaseQuery.Select("USERLIFESTYLE", userLifeStyle);

            return userlifestyleList;
        }
    }

    public static class UserMeal {
        public long id = 0;
        public String userName = "";
        public int type = 0;
        public long mealId = 0;

        Map<String, String> getUserMealObject() {
            Map<String, String> userMeal = DatabaseQuery.GetParameterList("USERMEAL");
            if (id == 0)
                userMeal.put("ID", "NULL");
            else
                userMeal.put("ID", String.valueOf(id));

            userMeal.put("USERNAME", userName.trim());
            userMeal.put("TYPE", String.valueOf(type));
            userMeal.put("MEALID", String.valueOf(mealId));

            return userMeal;
        }

        UserMeal(String usernameInput, int typeInput, long mealIdInput) {
            userName = usernameInput;
            type = typeInput;
            mealId = mealIdInput;
        }

        public static List<Map<Object, Object>> getUserMealList(String userName) {
            if (userName.contentEquals("") || userName == null)
                return null;

            Map<String, String> userMeal = DatabaseQuery.GetParameterList("USERMEAL");
            if (userName == null)
                userMeal.remove("USERNAME");
            else
                userMeal.put("USERNAME", userName.trim());

            userMeal.remove("ID");
            userMeal.remove("TYPE");
            userMeal.remove("MEALID");

            List<Map<Object, Object>> userMealList = DatabaseQuery.Select("USERMEAL", userMeal);

            return userMealList;
        }
    }

    public static class UserDatalog {
        public long id = 0;
        public String userName = "";
        public Float glucose = 0f;
        public Float carbonhydrade = 0f;
        public Float insuline = 0f;
        public Float weight = 0f;
        public Float hba1c = 0f;
        public Float colesterol = 0f;
        public int category = 0;
        public Float calori = 0f;

        Map<String, String> getUserDatalogObject() {
            Map<String, String> userDatalog = DatabaseQuery.GetParameterList("USERDATALOG");
            if (id == 0)
                userDatalog.put("ID", "NULL");
            else
                userDatalog.put("ID", String.valueOf(id));

            userDatalog.put("USERNAME", userName.trim());
            userDatalog.put("GLUCOSE", String.valueOf(glucose));
            userDatalog.put("CARBONHYDRADE", String.valueOf(carbonhydrade));
            userDatalog.put("INSULINE", String.valueOf(insuline));
            userDatalog.put("WEIGHT", String.valueOf(weight));
            userDatalog.put("HBA1C", String.valueOf(hba1c));
            userDatalog.put("COLESTEROL", String.valueOf(colesterol));
            userDatalog.put("CATEGORY", String.valueOf(category));
            userDatalog.put("CALORI", String.valueOf(calori));

            Date recordDate= Calendar.getInstance().getTime();

            userDatalog.put("DATETIME", Converter.DateToString(recordDate));

            return userDatalog;
        }

        UserDatalog(String usernameInput, Float glucoseInput, Float carbonhydradeInput, Float insulineInput,
                    Float weightInput, Float hba1cInput, Float colesterolInput, int categoryInput,
                    Float caloriInput) {

            userName = usernameInput;
            glucose = glucoseInput;
            carbonhydrade = carbonhydradeInput;
            insuline = insulineInput;
            weight = weightInput;
            hba1c = hba1cInput;
            colesterol = colesterolInput;
            category = categoryInput;
            calori = caloriInput;
        }

        public static List<Map<Object, Object>> getUserDatalogList(String userName) {
            if (userName.contentEquals("") || userName == null)
                return null;

            Map<String, String> userDatalog = DatabaseQuery.GetParameterList("USERDATALOG");
            if (userName == null)
                userDatalog.remove("USERNAME");
            else
                userDatalog.put("USERNAME", userName.trim());

            userDatalog.remove("ID");
            userDatalog.remove("GLUCOSE");
            userDatalog.remove("CARBONHYDRADE");
            userDatalog.remove("INSULINE");
            userDatalog.remove("WEIGHT");
            userDatalog.remove("HBA1C");
            userDatalog.remove("COLESTEROL");
            userDatalog.remove("CATEGORY");
            userDatalog.remove("CALORI");
            userDatalog.remove("DATETIME");

            List<Map<Object, Object>> userDatalogList = DatabaseQuery.Select("USERDATALOG", userDatalog);

            return userDatalogList;
        }
    }

    public static class MealCategory {
        public long id = 0;
        public String name = "";
        public String imageName = "";

        Map<String, String> getMealCategoryObject() {
            Map<String, String> mealCategory = DatabaseQuery.GetParameterList("MEALCATEGORY");
            if (id == 0)
                mealCategory.put("ID", "NULL");
            else
                mealCategory.put("ID", String.valueOf(id));

            mealCategory.put("NAME", name.trim());
            mealCategory.put("IMAGE", imageName.trim());

            return mealCategory;
        }

        MealCategory(String nameInput, String imageNameInput) {

            name = nameInput;
            imageName = imageNameInput;
        }

        public static List<Map<Object, Object>> getMealCategoryList(Long id) {
            Map<String, String> mealCategory = DatabaseQuery.GetParameterList("MEALCATEGORY");
            if (id == Long.MIN_VALUE)
                mealCategory.remove("ID");
            else
                mealCategory.put("ID", String.valueOf(id));

            mealCategory.remove("NAME");
            mealCategory.remove("IMAGE");

            List<Map<Object, Object>> mealCategoryList = DatabaseQuery.Select("MEALCATEGORY", mealCategory);

            return mealCategoryList;
        }
    }

    public static class Meal {
        public long id = 0;
        public long mealCategoryId = 0;
        public String name = "";
        public Float calori = 0f;
        public Float fat = 0f;
        public Float protein = 0f;
        public Float carbonhydrade = 0f;
        public String imageName = "";

        Map<String, String> getMealObject() {
            Map<String, String> meal = DatabaseQuery.GetParameterList("MEAL");
            if (id == 0)
                meal.put("ID", "NULL");
            else
                meal.put("ID", String.valueOf(id));

            meal.put("MEALCATEGORYID", String.valueOf(id));
            meal.put("NAME", name.trim());
            meal.put("CALORI", String.valueOf(calori));
            meal.put("FAT", String.valueOf(fat));
            meal.put("PROTEIN", String.valueOf(protein));
            meal.put("CARBONHYDRADE", String.valueOf(carbonhydrade));
            meal.put("IMAGE", imageName.trim());

            return meal;
        }

        Meal(Long mealCategoryIdInput, String nameInput, Float caloriInput, Float fatInput, Float proteinInput, Float carbonhydradeInput ,
             String imageNameInput) {

            mealCategoryId=mealCategoryIdInput;
            name = nameInput;
            calori=caloriInput;
            fat=fatInput;
            protein=proteinInput;
            carbonhydrade=carbonhydradeInput;
            imageName = imageNameInput;
        }

        public static List<Map<Object, Object>> getMealList(Long mealCategoryId) {
            if (mealCategoryId == Long.MIN_VALUE)
                return null;

            Map<String, String> meal = DatabaseQuery.GetParameterList("MEAL");
            if (mealCategoryId == Long.MIN_VALUE)
                meal.remove("MEALCATEGORYID");
            else
                meal.put("MEALCATEGORYID", mealCategoryId.toString());

            meal.remove("ID");
            meal.remove("NAME");
            meal.remove("CALORI");
            meal.remove("FAT");
            meal.remove("PROTEIN");
            meal.remove("CARBONHYDRADE");
            meal.remove("IMAGE");

            List<Map<Object, Object>> mealList = DatabaseQuery.Select("MEAL", meal);

            return mealList;
        }
    }

    public static class Contact {
        public long id = 0;
        public String userName = "";
        public String phoneNumber = "";
        public String email = "";
        public String facebookId = "";
        public String twitterId = "";

        Map<String, String> getContactObject() {
            Map<String, String> contact = DatabaseQuery.GetParameterList("CONTACT");
            if (id == 0)
                contact.put("ID", "NULL");
            else
                contact.put("ID", String.valueOf(id));

            contact.put("USERNAME", userName);
            contact.put("PHONENUMBER", phoneNumber);
            contact.put("EMAIL", email);
            contact.put("FACEBOOKID", facebookId);
            contact.put("TWITTERID", twitterId);

            return contact;
        }

        Contact(String usernameInput, String phoneNumberInput, String emailInput, String facebookIdInput, String twitterIdInput) {

            userName=usernameInput;
            phoneNumber = phoneNumberInput;
            email=emailInput;
            facebookId=facebookIdInput;
            twitterId=twitterIdInput;
        }

        public static List<Map<Object, Object>> getContactList(String userName) {
            if (userName.contentEquals("") || userName == null)
                return null;

            Map<String, String> contact = DatabaseQuery.GetParameterList("CONTACT");
            if (userName == null)
                contact.remove("USERNAME");
            else
                contact.put("USERNAME", userName.trim());

            contact.remove("ID");
            contact.remove("PHONENUMBER");
            contact.remove("EMAIL");
            contact.remove("FACEBOOKID");
            contact.remove("TWITTERID");

            List<Map<Object, Object>> contactList = DatabaseQuery.Select("CONTACT", contact);

            return contactList;
        }
    }
}