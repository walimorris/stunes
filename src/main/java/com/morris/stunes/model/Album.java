package com.morris.stunes.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @Column(name = "AlbumId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArtistId")
    private Artist artist;

    @Column(name = "Title")
    private String title;

    public Album() {}

    public Album(String title) {
        this.title = title;
    }

    public int getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
