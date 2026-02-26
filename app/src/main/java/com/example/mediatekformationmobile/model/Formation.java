package com.example.mediatekformationmobile.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Formation implements Serializable {

    private static final String CHEMINIMAGE = "https://i.ytimg.com/vi/";
    private int id;
    @SerializedName("playlist_id")
    private int playlistId;
    @SerializedName("published_at")
    private Date publishedAt;
    private String title;
    private String description;
    @SerializedName("video_id")
    private String videoId;
    private boolean favorite;

    /**
     * Constructeur : valorise les propriétés privées
     * @param id
     * @param playlistId
     * @param publishedAt
     * @param title
     * @param description
     * @param videoId
     */
    public Formation(int id, int playlistId, Date publishedAt, String title, String description, String videoId) {
        this.id = id;
        this.playlistId = playlistId;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.videoId = videoId;
        this.favorite = false;
    }

    public int getId() {
        return id;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMiniature() {
        return CHEMINIMAGE + videoId +"/default.jpg";
    }

    public String getPicture() {
        return CHEMINIMAGE + videoId +"/mqdefault.jpg";
    }

    public String getVideoId() {
        return videoId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
