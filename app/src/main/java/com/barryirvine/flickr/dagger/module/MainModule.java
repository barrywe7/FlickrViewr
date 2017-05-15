package com.barryirvine.flickr.dagger.module;


import com.barryirvine.flickr.interactors.InteractorContracts;
import com.barryirvine.flickr.ui.presenter.MainPresenter;
import com.barryirvine.flickr.ui.contract.MainContracts;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    @Singleton
    MainContracts.Presenter providePresenter(final InteractorContracts.PhotoApi photoApi) {
        return new MainPresenter(photoApi);
    }
}
