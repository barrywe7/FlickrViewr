package com.barryirvine.flickr.dagger.module;

import com.barryirvine.flickr.model.mappers.PhotoResponseMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {
    @Provides
    @Singleton
    PhotoResponseMapper providePhotoResponseMapper() {
        return new PhotoResponseMapper();
    }
}
