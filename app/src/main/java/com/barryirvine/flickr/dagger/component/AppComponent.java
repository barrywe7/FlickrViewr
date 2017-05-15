package com.barryirvine.flickr.dagger.component;

import com.barryirvine.flickr.dagger.module.AppModule;
import com.barryirvine.flickr.dagger.module.InteractorModule;
import com.barryirvine.flickr.dagger.module.MainModule;
import com.barryirvine.flickr.dagger.module.MapperModule;
import com.barryirvine.flickr.dagger.module.NetModule;
import com.barryirvine.flickr.ui.activity.MainActivity;
import com.barryirvine.flickr.ui.activity.PhotoDetailsActivity;
import com.barryirvine.flickr.ui.fragment.PhotoFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, NetModule.class, InteractorModule.class, MainModule.class, MapperModule.class})
public interface AppComponent {

    void inject(final PhotoFragment fragment);

    void inject(final PhotoDetailsActivity activity);

}
