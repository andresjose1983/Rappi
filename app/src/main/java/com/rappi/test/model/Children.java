package com.rappi.test.model;

import java.io.Serializable;

/**
 * Created by Mendez Fernandez on 29/12/2016.
 */

public class Children implements Serializable {

    private String id;
    private String banner_img;
    private String display_name;
    private String submit_text;//
    private String header_img;//
    private String title;//
    private String icon_img;//
    private String header_title;
    private long created;//

    public Children(String id, String banner_img, String display_name, String submit_text,
                    String header_img, String title, String icon_img, String header_title, long created) {
        this.id = id;
        this.banner_img = banner_img;
        this.display_name = display_name;
        this.submit_text = submit_text;
        this.header_img = header_img;
        this.title = title;
        this.icon_img = icon_img;
        this.header_title = header_title;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getBannerImg() {
        return banner_img;
    }

    public String getDisplayName() {
        return display_name;
    }

    public String getSubmitText() {
        return submit_text;
    }

    public String getHeaderImg() {
        return header_img;
    }

    public String getTitle() {
        return title;
    }

    public String getIconImg() {
        return icon_img;
    }

    public String getHeaderTitle() {
        return header_title;
    }

    public long getCreated() {
        return created;
    }
}
