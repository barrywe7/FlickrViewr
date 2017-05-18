package com.barryirvine.flickr.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.barryirvine.flickr.BR;
import com.barryirvine.flickr.R;
import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.ui.contract.MainContracts;
import com.barryirvine.flickr.viewmodel.PhotoListViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private final List<FlickrPhoto> mItems;
    private final Picasso mPicasso;
    private MainContracts.Presenter mPresenter;

    public PhotosAdapter(final List<FlickrPhoto> items, final Picasso picasso, final MainContracts.Presenter presenter) {
        mItems = items;
        mPicasso = picasso;
        mPresenter = presenter;

    }

    public List<FlickrPhoto> getItems() {
        return mItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, @LayoutRes final int viewType) {
        return new PhotosAdapter.ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false));
    }

    @LayoutRes
    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_photo;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(new PhotoListViewModel(holder.itemView.getContext(), mItems.get(position), mPresenter));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding mBinding;

        ViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(@NonNull final PhotoListViewModel photoViewModel) {
            mBinding.setVariable(BR.viewModel, photoViewModel);
            mBinding.setVariable(BR.picasso, mPicasso);
            mBinding.executePendingBindings();
        }
    }
}
