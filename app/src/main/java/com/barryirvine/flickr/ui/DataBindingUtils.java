package com.barryirvine.flickr.ui;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.barryirvine.flickr.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DataBindingUtils {

    @BindingAdapter({"imageUrl", "picasso"})
    public static void loadImage(@NonNull final ImageView view, @NonNull final String imageUrl, @NonNull final Picasso picasso) {
        picasso.load(imageUrl)
                .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                .placeholder(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_image_grey_24dp))
                .centerInside()
                .into(view);
    }

    @BindingAdapter({"croppedImageUrl", "picasso"})
    public static void loadCroppedImage(@NonNull final ImageView view, @NonNull final String imageUrl, @NonNull final Picasso picasso) {
        picasso.load(imageUrl)
                .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                .placeholder(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_image_grey_24dp))
                .centerCrop()
                .into(view);
    }

    @BindingAdapter({"imageUrl", "picasso", "callback"})
    public static void loadImage(@NonNull final ImageView view, @NonNull final String largeImageUrl, @NonNull final Picasso picasso, @Nullable Callback callback) {
        picasso.load(largeImageUrl)
                .noFade()
                .into(view, callback);
    }

}
