package com.barryirvine.flickr.interactors;


import com.barryirvine.flickr.api.FlickrAPI;
import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.model.mappers.PhotoResponseMapper;
import com.barryirvine.flickr.model.server.PhotoResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PhotoData implements InteractorContracts.PhotoApi {
    private final FlickrAPI mFlickrAPI;
    private final PhotoResponseMapper mPhotoResponseMapper;

    public PhotoData(final FlickrAPI flickrAPI, final PhotoResponseMapper mapper) {
        mFlickrAPI = flickrAPI;
        mPhotoResponseMapper = mapper;
    }

    @Override
    public Observable<List<FlickrPhoto>> getPhotos() {
        return mFlickrAPI.getPhotos()
                .map(photoResponse ->mPhotoResponseMapper.map(photoResponse))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
