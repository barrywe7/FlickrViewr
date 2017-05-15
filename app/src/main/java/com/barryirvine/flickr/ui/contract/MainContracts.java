package com.barryirvine.flickr.ui.contract;

import android.support.v4.widget.SwipeRefreshLayout;

import com.barryirvine.flickr.model.local.FlickrPhoto;

import java.util.List;

public interface MainContracts {
    interface View extends SwipeRefreshLayout.OnRefreshListener {
        void onLoading(final boolean loading);

        void onDataLoaded(final List<FlickrPhoto> photos);

        void showError(final String message);
    }

    interface Presenter {

        void attachView(final View view);

        void detachView();

        void loadData();

    }
}