package com.barryirvine.flickr.model.mappers;


import android.content.Context;

import com.barryirvine.flickr.R;
import com.barryirvine.flickr.model.local.FlickrPhoto;
import com.barryirvine.flickr.model.server.Photo;
import com.barryirvine.flickr.model.server.PhotoResponse;

import java.util.ArrayList;
import java.util.List;


public class PhotoResponseMapper {
    public List<FlickrPhoto> map(PhotoResponse photoResponse, final Context context) {
        if (photoResponse == null || photoResponse.getPhotos() == null || photoResponse.getPhotos().size() == 0) {
            return null;
        }

        final List<FlickrPhoto> photos = new ArrayList<>(photoResponse.getPhotos().size());

        for (final Photo photo : photoResponse.getPhotos()) {
            final String title = photo.getTitle();
            final String strTitle = title == null || title.trim().isEmpty() ? context.getString(R.string.no_title) : title.trim();
            photos.add(new FlickrPhoto.Builder()
                    .author(photo.getAuthor())
                    .authorId(photo.getAuthorId())
                    .dateTaken(photo.getDateTaken())
                    .description(photo.getDescription())
                    .link(photo.getLink())
                    .photoUrl(photo.getMedia())
                    .publishedDate(photo.getPublishedDate())
                    .tags(photo.getTags())
                    .title(strTitle)
                    .build());
        }

        return photos;
    }
}
