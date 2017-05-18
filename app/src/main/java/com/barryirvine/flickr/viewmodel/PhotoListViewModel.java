package com.barryirvine.flickr.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.model.server.Photo;
import com.barryirvine.flickr.ui.UiUtils;
import com.barryirvine.flickr.ui.activity.PhotoDetailsActivity;
import com.barryirvine.flickr.ui.contract.MainContracts;

import java.text.DateFormat;

/**
 * View Model for {@link Photo} Remember to use the {@link Bindable} annotation for all getters and to do
 * {@link #notifyPropertyChanged(int)} after a value is set in a setter.
 */

public class PhotoListViewModel extends BaseObservable {

    private final Context mContext;
    private final FlickrPhoto mPhoto;
    private final MainContracts.Presenter mPresenter;


    public PhotoListViewModel(@NonNull final Context context, final FlickrPhoto photo, final MainContracts.Presenter presenter) {
        mContext = context;
        mPhoto = photo;
        mPresenter = presenter;
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

    public void onClick(final View view) {
        if (mPresenter.areClicksEnabled()) {
            PhotoDetailsActivity.start(mContext, mPhoto, UiUtils.getArtworkActivityOptions((Activity) mContext, view));
            mPresenter.setClicksEnabled(false);
        }
    }
}
