package com.rappi.test.model;

/**
 * Created by Mendez Fernandez on 29/12/2016.
 */

public class RedditResponse {

    private String kind;
    private Data data;

    public RedditResponse(String kind, Data data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public Data getData() {
        return data;
    }
}
