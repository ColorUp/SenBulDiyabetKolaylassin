package com.mycodeyourproject.senbuldiyabetkolaylassin;

/**
 * Created by Burak on 10.09.2015.
 */
public class SpinnerModel {
    private String name = "";
    private String image = "";

    /***********
     * Set Methods
     ******************/
    public void setCompanyName(String Name) {
        this.name = Name;
    }

    public void setImage(String Image) {
        this.image = Image;
    }

    /***********
     * Get Methods
     ****************/
    public String getCompanyName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }
}
