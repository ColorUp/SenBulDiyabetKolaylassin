package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.util.ArrayList;

/**
 * Created by Burak on 05.10.2015.
 */
public class Parent {

    private long id;
    private String name;
    private String summary;
    private boolean checked;
    private float totalCal;
    private float totalFat;
    private float totalCarbonhydrade;
    private float totalProtein;

    // ArrayList to store child objects
    private ArrayList<Child> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public float getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(float totalCal) {
        this.totalCal = totalCal;
    }

    public float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(float totalFat) {
        this.totalFat = totalFat;
    }

    public float getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(float
                                        totalProtein) {
        this.totalProtein = totalProtein;
    }

    public float getTotalCarbonhydrade() {
        return totalCarbonhydrade;
    }

    public void setTotalCarbonhydrade(float totalCarbonhydrade) {
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