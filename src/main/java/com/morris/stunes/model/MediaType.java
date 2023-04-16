package com.morris.stunes.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "media_types")
public class MediaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaTypeId")
    private int mediaTypeId;

    @Column(name = "Name")
    private String name;

    public MediaType() {}

    public MediaType(int mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public MediaType(String name) {
        this.name = name;
    }

    public int getMediaTypeId() {
        return this.mediaTypeId;
    }

    public void setMediaTypeId(int mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
