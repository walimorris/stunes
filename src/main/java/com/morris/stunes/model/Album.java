package com.morris.stunes.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @Column(name = "AlbumId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;

    private int artistId;

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

    public int getArtistId() {
        return this.artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
