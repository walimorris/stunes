package com.morris.stunes.model;

public class Genre {
    private int genreId;
    private String name;

    public Genre() {}

    public Genre(int genreId) {
        this.genreId = genreId;
    }

    public Genre(String name) {
        this.name = name;
    }

    public int getGenreId() {
        return this.genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
