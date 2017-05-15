package com.barryirvine.flickr;

import android.app.Application;

import com.barryirvine.flickr.api.FlickrAPI;
import com.barryirvine.flickr.dagger.component.AppComponent;
import com.barryirvine.flickr.dagger.component.DaggerAppComponent;
import com.barryirvine.flickr.dagger.module.AppModule;
import com.barryirvine.flickr.dagger.module.NetModule;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(FlickrAPI.BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
