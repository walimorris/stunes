package com.morris.stunes.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name = "ArtistId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Image")
    private String image;

    public Artist() {}

    public Artist(String name) {
        this.name = name;
    }

    public int getArtistId() {
        return this.artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.image;
    }

    public void setImageUrl(String imageUrl) {
        this.image = imageUrl;
    }

    @Override
    public String toString() {
        return "Artist(artistId=" + this.artistId + ", Name=" + this.name + ")";
    }
}
