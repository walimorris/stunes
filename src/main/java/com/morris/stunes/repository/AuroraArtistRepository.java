package com.morris.stunes.repository;

import com.morris.stunes.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuroraArtistRepository extends JpaRepository<Artist, Integer> {

    Artist findByName(String name);

    @Query( value = "SELECT ArtistId, Name, Image FROM artists WHERE Name LIKE concat('%', ?1, '%') OR Name LIKE concat(?2, '%')",
            nativeQuery = true)
    List<Artist> findByNameLike(String name1, String name2);

    @Query("SELECT a FROM Artist a WHERE a.name LIKE %:name%")
    Page<Artist> findByNameIsLikeIgnoreCase(@Param("name") String name, Pageable paging);
}
