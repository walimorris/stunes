package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Album;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AuroraAlbumRepositoryTests {

    @Autowired
    AuroraAlbumRepository auroraAlbumRepository;

    @Test
    public void testFindByTitleLike() {
        String title1 = "Let There";
        String title2 = "Balls to the";
        List<Album> albums = auroraAlbumRepository.findByTitleLike(title1, title1);
        List<Album> albums2 = auroraAlbumRepository.findByTitleLike(title2, title2);

        assertAll(
                () -> assertTrue(albums.size() > 0),
                () -> assertEquals(1, albums.get(0).getArtist().getArtistId()),
                () -> assertTrue(albums2.size() > 0),
                () -> assertEquals(2, albums2.get(0).getArtist().getArtistId()),
                () -> assertTrue(albums.get(0).getImageUrl().contains("let-there-be-rock")),
                () -> assertTrue(albums2.get(0).getImageUrl().contains("balls-to-the-wall"))
        );
    }

    @Test
    public void testFindByArtistId() {
        int artistId = 2;

        List<Album> albums = auroraAlbumRepository.findByArtistId(artistId);

        assertAll(
                () -> assertEquals(2, albums.size()),
                () -> assertEquals("Balls to the Wall", albums.get(0).getTitle()),
                () -> assertEquals("Restless and Wild", albums.get(1).getTitle())
        );
    }

    @Test
    public void findByArtistIdPageable() {
        int artistId = 2;
        Pageable pageableProperties = PageRequest.of(0, 12);
        Page<Album> artistByIdPageableList = auroraAlbumRepository.findByArtistId(artistId, pageableProperties);
        List<Album> artistByIdList = artistByIdPageableList.getContent();

        assertAll(
                () -> assertEquals(1, artistByIdPageableList.getTotalPages()),
                () -> assertEquals(1, artistByIdPageableList.getTotalElements())
        );
    }

    @Test
    public void findByTitleIsLikeIgnoreCaseTest() {
        String queryString = "live";
        Pageable pagingProperties = PageRequest.of(0, 12);
        Page<Album> titleLikePageableList = auroraAlbumRepository.findByTitleIsLikeIgnoreCase(queryString, pagingProperties);
        List<Album> titleLikeList = titleLikePageableList.getContent();

        assertAll(
                () -> assertEquals(2, titleLikePageableList.getTotalPages()),
                () -> assertEquals(17, titleLikePageableList.getTotalElements()),
                () -> assertInstanceOf(Album.class, titleLikeList.get(0))
        );
    }
}
