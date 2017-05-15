package com.barryirvine.flickr.ui.presenter;

import com.barryirvine.flickr.interactors.InteractorContracts;
import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.ui.contract.MainContracts;

import java.util.Collections;
import java.util.List;

public class MainPresenter implements MainContracts.Presenter {

    private final InteractorContracts.PhotoApi mPhotoApi;

    private boolean loading;

    private MainContracts.View mView;

    private List<FlickrPhoto> mPendingResult = Collections.emptyList();
    private String mPendingError = "";


    public MainPresenter(final InteractorContracts.PhotoApi photoApi) {
        mPhotoApi = photoApi;
    }

    @Override
    public void attachView(final MainContracts.View view) {
        mView = view;
        mView.onLoading(loading);
        if (!mPendingError.isEmpty()) {
            mView.showError(mPendingError);
            mPendingError = "";
        } else if (!mPendingResult.isEmpty()) {
            mView.onDataLoaded(mPendingResult);
            mPendingResult = Collections.emptyList();
        }
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void loadData() {
        loading = true;
        mPhotoApi.getPhotos().subscribe(
                photos -> {
                    loading = false;
                    if (mView != null) {
                        mView.onLoading(false);
                        if (photos != null) {
                            mView.onDataLoaded(photos);
                        }
                    } else {
                        mPendingResult = photos;
                    }
                },
                throwable -> {
                    loading = false;
                    if (mView != null) {
                        mView.onLoading(false);
                        mView.showError(throwable.getMessage());
                    } else {
                        mPendingError = throwable.getMessage();
                    }
                });
    }

}