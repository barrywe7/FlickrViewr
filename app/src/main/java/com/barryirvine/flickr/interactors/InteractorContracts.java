package com.barryirvine.flickr.interactors;

import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.model.server.Photo;
import com.barryirvine.flickr.model.server.PhotoResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public interface InteractorContracts {
    interface PhotoApi {
		Observable<List<FlickrPhoto>> getPhotos();
	}
}
