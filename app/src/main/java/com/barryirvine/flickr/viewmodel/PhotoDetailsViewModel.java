package com.barryirvine.flickr.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;

import com.barryirvine.flickr.R;
import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.model.server.Photo;
import com.barryirvine.flickr.ui.activity.PhotoDetailsActivity;

import java.text.DateFormat;

/**
 * View Model for {@link Photo} Remember to use the {@link Bindable} annotation for all getters and to do
 * {@link #notifyPropertyChanged(int)} after a value is set in a setter.
 */

public class PhotoDetailsViewModel extends BaseObservable {

    private final Context mContext;
    private final FlickrPhoto mPhoto;


    public PhotoDetailsViewModel(@NonNull final Context context, final FlickrPhoto photo) {
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
    public String getPublishedDateAsString() {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(mPhoto.getPublishedDate());
    }

    @Bindable
    public String getDateTakenAsString() {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(mPhoto.getDateTaken());
    }

    @Bindable
    public Spanned getDescription() {
        return Html.fromHtml(mPhoto.getDescription().replaceAll("(<(/)img>)|(<img.+?>)", "").trim());
    }

    @Bindable
    public String getAuthor() {
        return mPhoto.getAuthor();
    }

    @Bindable
    public String getTags() {
        return mPhoto.getTags();
    }
}