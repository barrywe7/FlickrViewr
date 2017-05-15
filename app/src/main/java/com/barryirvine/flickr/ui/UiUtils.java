package com.barryirvine.flickr.ui;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.Window;

import com.barryirvine.flickr.R;

import java.util.ArrayList;
import java.util.List;

public class UiUtils {

    public static ActivityOptionsCompat getArtworkActivityOptions(final Activity activity, @NonNull final View imageView) {
        final List<Pair<View, String>> pairs = new ArrayList<>();
        pairs.add(Pair.create(imageView, activity.getString(R.string.artwork_transition)));
        addPair(activity.getWindow().getDecorView().findViewById(android.R.id.statusBarBackground), Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME, pairs);
        addPair(activity.getWindow().getDecorView().findViewById(android.R.id.navigationBarBackground), Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME, pairs);
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs.toArray(new Pair[pairs.size()]));
    }

    private static void addPair(@Nullable final View view, @NonNull final String transitionName, @NonNull final List<Pair<View, String>> pairs) {
        if (view != null) {
            pairs.add(Pair.create(view, transitionName));
        }
    }
}
