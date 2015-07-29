package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burak on 28.07.2015.
 */
public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }
}