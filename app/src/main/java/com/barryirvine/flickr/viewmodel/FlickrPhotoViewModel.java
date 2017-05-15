package com.barryirvine.flickr.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;

import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.model.server.Photo;

import java.util.Date;

/**
 * View Model for {@link Photo} Remember to use the {@link Bindable} annotation for all getters and to do
 * {@link #notifyPropertyChanged(int)} after a value is set in a setter.
 */

public class FlickrPhotoViewModel extends BaseObservable {

    private final Context mContext;
    private final FlickrPhoto mPhoto;


    public FlickrPhotoViewModel(@NonNull final Context context, final FlickrPhoto photo) {
        mContext = context;
        mPhoto = photo;
    }

    @Bindable
    public String getImageUrl() {
        return mPhoto.getPhotoUrl();
    }

    @Bindable
    public String getTitle() {
        return mPhoto.getTitle();
    }

    @Bindable
    public Date getPublishedDate() {
        return mPhoto.getPublishedDate();
    }

    @Bindable
    public Spanned getDescription() {
        return Html.fromHtml(mPhoto.getDescription());
    }

    public void onClick(final ImageView view) {
        //TODO:
    }




    /*@Bindable
    public String getName() {
        return mBeer.getName();
    }

    @Bindable
    public String getDescription() {
        return mBeer.getDescription();
    }

    @Bindable
    public String getAbv() {
        return String.valueOf(mBeer.getAbv()) +"%";
    }

    @Bindable
    public String getTagline() {
        return mBeer.getTagline();
    }



    public void onClick() {
        // Haven't worked out a safe way of passing the view from view model for use in the shared transitions yet
        BeerDetailsActivity.start(mContext, mBeer, null);
    }*/
}