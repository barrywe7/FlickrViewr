package com.barryirvine.flickr.ui.presenter;

import com.barryirvine.flickr.interactors.InteractorContracts;
import com.barryirvine.flickr.ui.contract.MainContracts;

import io.reactivex.functions.Consumer;

public class MainPresenter implements MainContracts.Presenter {

    private final InteractorContracts.PhotoApi mPhotoApi;

    private boolean loading;

    private MainContracts.View mView;


    public MainPresenter(final InteractorContracts.PhotoApi photoApi) {
        mPhotoApi = photoApi;
    }

    @Override
    public void attachView(final MainContracts.View view) {
        mView = view;
        mView.onLoading(loading);
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
                    }
                },
                throwable -> {
                    loading = false;
                    if (mView != null) {
                        mView.onLoading(false);
                        mView.showError("Error retrieving photos: " + throwable.getMessage());
                    }
                });
        ;
    }

}