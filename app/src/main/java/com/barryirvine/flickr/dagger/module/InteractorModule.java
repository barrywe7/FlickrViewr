package com.barryirvine.flickr.dagger.module;

import android.content.Context;

import com.barryirvine.flickr.api.FlickrAPI;
import com.barryirvine.flickr.interactors.PhotoData;
import com.barryirvine.flickr.interactors.InteractorContracts;
import com.barryirvine.flickr.model.mappers.PhotoResponseMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
	@Provides
	@Singleton
	InteractorContracts.PhotoApi providePhotoData(final FlickrAPI flickrAPI, final PhotoResponseMapper mapper, final Context context) {
		return new PhotoData(flickrAPI, mapper, context);
	}
}
