package com.barryirvine.flickr.model.server;

import java.util.Date;

@SuppressWarnings("unused")
public class Photo {
    private String author;
    private String authorId;
    private Date dateTaken;
    private String description;
    private String link;
    private Media media;
    private Date published;
    private String tags;
    private String title;

    public Photo() {
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public Media getMedia() {
        return media;
    }

    public Date getPublishedDate() {
        return published;
    }

    public String getTags() {
        return this.tags;
    }

    public String getTitle() {
        return title;
    }
}
