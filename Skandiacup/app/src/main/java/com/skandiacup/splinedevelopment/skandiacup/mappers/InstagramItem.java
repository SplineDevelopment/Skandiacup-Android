package com.skandiacup.splinedevelopment.skandiacup.mappers;

/**
 * Created by hoxmark on 05/11/15.
 */
public class InstagramItem {
    String id = "";
    String url = "";
    String thumbnailUrl = "";
    String dato = "";
    String user = "";
    String userPhotoURL = "";

    int widthOfImage = 0;
    int heightOfImage = 0;


    public String getUserPhotoURL() {
        return userPhotoURL;
    }

    public void setUserPhotoURL(String userPhotoURL) {
        this.userPhotoURL = userPhotoURL;
    }



    public InstagramItem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InstagramItem(String url, String thumbnailUrl, String dato, String user) {
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.dato = dato;
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getWidthOfImage() {
        return widthOfImage;
    }

    public void setWidthOfImage(int widthOfImage) {
        this.widthOfImage = widthOfImage;
    }

    public int getHeightOfImage() {
        return heightOfImage;
    }

    public void setHeightOfImage(int heightOfImage) {
        this.heightOfImage = heightOfImage;
    }
}
