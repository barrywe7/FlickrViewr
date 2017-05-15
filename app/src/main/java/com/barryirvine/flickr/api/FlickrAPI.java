package com.barryirvine.flickr.api;


import com.barryirvine.flickr.model.server.PhotoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FlickrAPI {

    String BASE_URL = "https://api.flickr.com/services/feeds/";

    @GET("photos_public.gne?format=json&nojsoncallback=1")
    Observable<PhotoResponse> getPhotos();

}
