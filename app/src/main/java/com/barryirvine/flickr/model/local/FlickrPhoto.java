package com.barryirvine.flickr.model.local;

import android.os.Parcel;
import android.os.Parcelable;

import com.barryirvine.flickr.model.server.Media;

import java.util.Date;

public class FlickrPhoto implements Parcelable {

    public static final Parcelable.Creator<FlickrPhoto> CREATOR = new Parcelable.Creator<FlickrPhoto>() {
        @Override
        public FlickrPhoto createFromParcel(final Parcel source) {
            return new FlickrPhoto(source);
        }

        @Override
        public FlickrPhoto[] newArray(final int size) {
            return new FlickrPhoto[size];
        }
    };
    private String mAuthor;
    private String mAuthorId;
    private Date mDateTaken;
    private String mDescription;
    private String mLink;
    private String mPhotoUrl;
    private Date mPublishedDate;
    private String mTags;
    private String mTitle;

    private FlickrPhoto(final Builder builder) {
        mAuthor = builder.mAuthor;
        mAuthorId = builder.mAuthorId;
        mDateTaken = builder.mDateTaken;
        mDescription = builder.mDescription;
        mLink = builder.mLink;
        mPhotoUrl = builder.mPhotoUrl;
        mPublishedDate = builder.mPublishedDate;
        mTags = builder.mTags;
        mTitle = builder.mTitle;
    }

    protected FlickrPhoto(final Parcel in) {
        this.mAuthor = in.readString();
        this.mAuthorId = in.readString();
        long tmpDateTaken = in.readLong();
        this.mDateTaken = tmpDateTaken == -1 ? null : new Date(tmpDateTaken);
        this.mDescription = in.readString();
        this.mLink = in.readString();
        this.mPhotoUrl = in.readString();
        long tmpPublishedDate = in.readLong();
        this.mPublishedDate = tmpPublishedDate == -1 ? null : new Date(tmpPublishedDate);
        this.mTags = in.readString();
        this.mTitle = in.readString();
    }

    public String getAuthorId() {
        return mAuthorId;
    }

    public String getLink() {
        return mLink;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public String getTags() {
        return mTags;
    }

    public String getDescription() {
        return mDescription;
    }

    public Date getPublishedDate() {
        return mPublishedDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDateTaken() {
        return mDateTaken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mAuthor);
        dest.writeString(this.mAuthorId);
        dest.writeLong(this.mDateTaken != null ? this.mDateTaken.getTime() : -1);
        dest.writeString(this.mDescription);
        dest.writeString(this.mLink);
        dest.writeString(this.mPhotoUrl);
        dest.writeLong(this.mPublishedDate != null ? this.mPublishedDate.getTime() : -1);
        dest.writeString(this.mTags);
        dest.writeString(this.mTitle);
    }

    public static class Builder {
        private String mAuthor;
        private String mAuthorId;
        private Date mDateTaken;
        private String mDescription;
        private String mLink;
        private String mPhotoUrl;
        private Date mPublishedDate;
        private String mTags;
        private String mTitle;

        public Builder author(final String author) {
            mAuthor = author;
            return this;
        }

        public Builder authorId(final String authorId) {
            mAuthorId = authorId;
            return this;
        }

        public Builder dateTaken(final Date dateTaken) {
            mDateTaken = dateTaken;
            return this;
        }

        public Builder description(final String description) {
            mDescription = description;
            return this;
        }

        public Builder link(final String link) {
            mLink = link;
            return this;
        }

        public Builder photoUrl(final Media media) {
            mPhotoUrl = media.getMedia();
            return this;
        }

        public Builder publishedDate(final Date publishedDate) {
            mPublishedDate = publishedDate;
            return this;
        }

        public Builder tags(final String tags) {
            mTags = tags;
            return this;
        }

        public Builder title(final String title) {
            mTitle = title;
            return this;
        }


        public FlickrPhoto build() {
            return new FlickrPhoto(this);
        }
    }
}
