package com.morris.stunes.model;

public class PlaylistTrack {
    private int playlistTrackId;
    private Track track;

    public PlaylistTrack() {}

    public PlaylistTrack(int playlistTrackId) {
        this.playlistTrackId = playlistTrackId;
    }

    public int getPlaylistTrackId() {
        return playlistTrackId;
    }

    public void setPlaylistTrackId(int playlistTrackId) {
        this.playlistTrackId = playlistTrackId;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
