package com.morris.stunes.repository;

import com.morris.stunes.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuroraAlbumRepository extends JpaRepository<Album, Integer> {

    @Query( value = "SELECT AlbumId, Title, ArtistId FROM albums WHERE Title LIKE concat('%', ?1, '%') OR Title LIKE concat(?2, '%')",
            nativeQuery = true)
    List<Album> findByTitleLike(String title1, String title2);

    @Query(value = "SELECT AlbumId, Title, ArtistId FROM albums WHERE ArtistId = ?1",
           nativeQuery = true)
    List<Album> findByArtistId(int artistId);
}
