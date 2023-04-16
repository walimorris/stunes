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

    Track findTrackByTrackId(int trackId);
}
