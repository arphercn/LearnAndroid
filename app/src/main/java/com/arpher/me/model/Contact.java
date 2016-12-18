package com.arpher.me.model;

/**
 * Created by ThinkPad on 2016/12/18.
 */

public class Contact {
    int id;
    Category category;
    String name;
    String url;

    public Contact() {

    }

    public Contact(String name, String url, Category category) {
        this.name = name;
        this.url  = url;
        this.category = category;
    }

    public Contact(int id, String name, String url, Category category) {
        this.id   = id;
        this.name = name;
        this.url  = url;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
