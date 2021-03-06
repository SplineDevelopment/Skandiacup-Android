/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.domain;

import android.text.Html;

import java.io.Serializable;

public class RSSObject implements Serializable{
    private String title;
    private String pubDate;
    private String category;
    private String itemDescription;

    public RSSObject(String title, String pubDate, String category, String itemDescription) {
        this.title = title;
        this.pubDate = pubDate;
        this.category = category;
        this.itemDescription = itemDescription;
    }

    public RSSObject() {
        this.title = "";
        this.pubDate = "";
        this.category = "";
        this.itemDescription = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemDescription() {
        return Html.fromHtml(itemDescription).toString();
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "RSSObject{" +
                "title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", category='" + category + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
