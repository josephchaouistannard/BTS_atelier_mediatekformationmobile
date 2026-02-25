package com.example.mediatekformationmobile.model;

import java.io.Serializable;
import java.util.Date;

public class Formation implements Serializable {

    private static final String CHEMINIMAGE = "https://i.ytimg.com/vi/";
    private int id;
    private int playlist_id;
    private Date published_at;
    private String title;
    private String description;
    private String video_id;
    private boolean favorite;

    /**
     * Constructeur : valorise les propriétés privées
     * @param id
     * @param playlist_id
     * @param published_at
     * @param title
     * @param description
     * @param video_id
     */
    public Formation(int id, int playlist_id, Date published_at, String title, String description, String video_id) {
        this.id = id;
        this.playlist_id = playlist_id;
        this.published_at = published_at;
        this.title = title;
        this.description = description;
        this.video_id = video_id;
        this.favorite = false;
    }

    public int getId() {
        return id;
    }

    public int getPlaylistId() {
        return playlist_id;
    }

    public Date getPublishedAt() {
        return published_at;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMiniature() {
        return CHEMINIMAGE + video_id +"/default.jpg";
    }

    public String getPicture() {
        return CHEMINIMAGE + video_id +"/mqdefault.jpg";
    }

    public String getVideoId() {
        return video_id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
