package com.rappi.test.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mendez Fernandez on 29/12/2016.
 */

public class Data implements Serializable{

    private String modhash;
    private String after;
    private String before;
    private List<ChildrenResponse> children = new ArrayList<>();

    public Data(String modhash, String after, String before, List<ChildrenResponse> children) {
        this.modhash = modhash;
        this.after = after;
        this.before = before;
        this.children = children;
    }

    public String getModhash() {
        return modhash;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }

    public List<ChildrenResponse> getChildren() {
        return children;
    }
}
