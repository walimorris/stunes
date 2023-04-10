package com.morris.stunes.model;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name = "artistId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistId;

    @Column(name = "name")
    private String name;

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
}
