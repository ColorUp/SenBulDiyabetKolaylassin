package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.util.ArrayList;

/**
 * Created by Burak on 05.10.2015.
 */
public class Parent {

    private int id;
    private String name;
    private String summary;
    private boolean checked;
    private int totalCal;
    private int totalFat;
    private int totalCarbonhydrade;
    private int totalProtein;

    // ArrayList to store child objects
    private ArrayList<Child> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(int totalCal) {
        this.totalCal = totalCal;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(int
                                        totalProtein) {
        this.totalProtein = totalProtein;
    }

    public int getTotalCarbonhydrade() {
        return totalCarbonhydrade;
    }

    public void setTotalCarbonhydrade(int totalCarbonhydrade) {
        this.totalCarbonhydrade = totalCarbonhydrade;
    }

    // ArrayList to store child objects
    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }
}