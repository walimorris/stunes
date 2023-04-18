package com.morris.stunes.repository;

import com.morris.stunes.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuroraAlbumRepository extends JpaRepository<Album, Integer> {

    @Query( value = "SELECT AlbumId, Title, ArtistId, Image FROM albums WHERE Title LIKE concat('%', ?1, '%') OR Title LIKE concat(?2, '%')",
            nativeQuery = true)
    List<Album> findByTitleLike(String title1, String title2);

    @Query("SELECT a FROM Album a WHERE a.title LIKE %:title%")
    Page<Album> findByTitleIsLikeIgnoreCase(@Param("title") String title, Pageable pageable);

    @Query(value = "SELECT AlbumId, Title, ArtistId, Image FROM albums WHERE ArtistId = ?1",
           nativeQuery = true)
    List<Album> findByArtistId(int artistId);

    @Query("SELECT a FROM Album a WHERE a.albumId = :artistId")
    Page<Album> findByArtistIdPageable(@Param("artistId") int artistId, Pageable pageable);
}
