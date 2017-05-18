package com.barryirvine.flickr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.barryirvine.flickr.App;
import com.barryirvine.flickr.R;
import com.barryirvine.flickr.databinding.FragmentPhotosBinding;
import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.ui.adapter.PhotosAdapter;
import com.barryirvine.flickr.ui.contract.MainContracts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class PhotoFragment extends Fragment implements MainContracts.View {

    @Inject
    Picasso mPicasso;
    @Inject
    MainContracts.Presenter mPresenter;
    private FragmentPhotosBinding mBinding;
    private PhotosAdapter mAdapter;

    public PhotoFragment() {
    }

    public static PhotoFragment newInstance() {
        final PhotoFragment fragment = new PhotoFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);
        if (savedInstanceState == null) {
            mPresenter.loadData();
        }
    }

    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Args.PHOTOS, new ArrayList<Parcelable>(mAdapter.getItems()));
        outState.putParcelable(Args.RECYCLER_VIEW_STATE, mBinding.recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    public void onStop() {
        mPresenter.detachView();
        super.onStop();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = DataBindingUtil.bind(view);
        if (savedInstanceState == null) {
            mAdapter = new PhotosAdapter(Collections.emptyList(), mPicasso, mPresenter);
            mBinding.recyclerView.setAdapter(mAdapter);
        } else {
            mBinding.recyclerView.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(Args.RECYCLER_VIEW_STATE));
            final ArrayList<FlickrPhoto> list = savedInstanceState.getParcelableArrayList(Args.PHOTOS);
            mAdapter = new PhotosAdapter(list, mPicasso, mPresenter);
            mBinding.recyclerView.setAdapter(mAdapter);
        }
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onLoading(final boolean loading) {
        mBinding.swipeRefreshLayout.setRefreshing(loading);
    }

    @Override
    public void onDataLoaded(final List<FlickrPhoto> photos) {
        mAdapter = new PhotosAdapter(photos, mPicasso, mPresenter);
        // Shouldn't have to do this - it's defined in XML but there looks to be a bug with SGLM when doing pull to refresh
        mBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getResources().getInteger(R.integer.feed_grid_columns), StaggeredGridLayoutManager.VERTICAL));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(final String message) {
        Toast.makeText(getContext(), getString(R.string.f_error_retrieving_photos, message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        mPresenter.loadData();
    }

    private class Args {
        private static final String PHOTOS = "PHOTOS";
        private static final String RECYCLER_VIEW_STATE = "RECYCLER_VIEW_STATE";

    }

}
