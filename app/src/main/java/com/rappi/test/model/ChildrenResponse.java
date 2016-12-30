package com.rappi.test.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 29/12/2016.
 */

public class ChildrenResponse implements Serializable {

    private String kind;
    private Children data;

    public ChildrenResponse(String kind, Children data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public Children getData() {
        return data;
    }
}
