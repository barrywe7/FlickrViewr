package com.barryirvine.flickr.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;

import com.barryirvine.flickr.App;
import com.barryirvine.flickr.R;
import com.barryirvine.flickr.databinding.ActivityPhotoDetailsBinding;
import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.viewmodel.PhotoDetailsViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class PhotoDetailsActivity extends AppCompatActivity {

    @Inject
    Picasso mPicasso;
    private ActivityPhotoDetailsBinding mBinding;
    private FlickrPhoto mPhoto;

    public static void start(@NonNull final Context context, @NonNull final FlickrPhoto photo, @Nullable final ActivityOptionsCompat options) {
        context.startActivity(makeIntent(context, photo), options != null ? options.toBundle() : null);
    }

    public static Intent makeIntent(@NonNull final Context context, final FlickrPhoto photo) {
        return new Intent(context, PhotoDetailsActivity.class)
                .putExtra(Extras.PHOTO, photo);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getAppComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_details);
        mPhoto = getIntent().getParcelableExtra(Extras.PHOTO);
        mBinding.setViewModel(new PhotoDetailsViewModel(this, mPhoto, mPicasso));
        mBinding.setCallback(new Callback() {
            @Override
            public void onSuccess() {
                ActivityCompat.startPostponedEnterTransition(PhotoDetailsActivity.this);
            }

            @Override
            public void onError() {
                ActivityCompat.startPostponedEnterTransition(PhotoDetailsActivity.this);
            }
        });
        setSupportActionBar(mBinding.toolbar);
        ActivityCompat.postponeEnterTransition(this);
    }

    private static class Extras {
        static final String PHOTO = FlickrPhoto.class.getSimpleName();
    }

}
