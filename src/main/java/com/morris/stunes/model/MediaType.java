package com.morris.stunes.model;

public class MediaType {
    private int mediaTypeId;
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
