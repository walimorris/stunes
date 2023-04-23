package com.morris.stunes.repository;

import com.morris.stunes.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuroraTrackRepository extends JpaRepository<Track, Integer> {

    @Query(value = "SELECT TrackId, Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds," +
            "Bytes, UnitPrice FROM tracks WHERE AlbumId = ?1",
           nativeQuery = true)
    List<Track> findTracksByAlbumId(int albumId);

    @Query(value = "SELECT t.TrackId, t.Name, t.AlbumId, t.MediaTypeId, t.GenreId, t.Composer, t.Milliseconds, " +
            "t.Bytes, t.UnitPrice FROM tracks as t JOIN invoice_items as i ON t.TrackId = i.TrackId GROUP BY " +
            "i.TrackId ORDER BY COUNT(i.TrackId) DESC LIMIT 10",
           nativeQuery = true)
    List<Track> findMostPopularTracks();

    Track findTrackByTrackId(int trackId);
}
