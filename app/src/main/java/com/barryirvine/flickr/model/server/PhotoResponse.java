package com.barryirvine.flickr.model.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoResponse {

    @SerializedName("items")
    private List<Photo> mPhotos;

    public List<Photo> getPhotos() {
        return mPhotos;
    }

}
