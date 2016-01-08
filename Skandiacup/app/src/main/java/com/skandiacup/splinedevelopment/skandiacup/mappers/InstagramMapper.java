/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.mappers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstagramMapper {
    ArrayList<InstagramItem> list = new ArrayList<>();

    public ArrayList<InstagramItem> mapFromJson(JSONObject response){
        try{
            JSONArray data = response.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject etbilde = data.getJSONObject(i);
                InstagramItem temp = new InstagramItem();


                //URL Object
                JSONObject imageURLs = etbilde.getJSONObject("images");

                //Setter bildeURL og Width og Height
                JSONObject fullSizeURL = imageURLs.getJSONObject("standard_resolution");
                String std_res = fullSizeURL.getString("url");
                int widthOfImage = fullSizeURL.getInt("width");
                int heightOfImage = fullSizeURL.getInt("height");
                temp.setUrl(std_res);
                temp.setWidthOfImage(widthOfImage);
                temp.setHeightOfImage(heightOfImage);

                //Setter Thumbnail-URL
                JSONObject thumbnailObject = imageURLs.getJSONObject("thumbnail");
                String thumbnail = thumbnailObject.getString("url");
                temp.setThumbnailUrl(thumbnail);

                //Setter user
                JSONObject userObject = etbilde.getJSONObject("user");
                temp.setUser(userObject.getString("username"));
                temp.setUserPhotoURL(userObject.getString("profile_picture"));

                //Created time
                String dato = etbilde.getString("created_time");
                temp.setDato(dato);


                //Set id

                String tempId = etbilde.getString("id");
                temp.setId(tempId);

                list.add(temp);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
