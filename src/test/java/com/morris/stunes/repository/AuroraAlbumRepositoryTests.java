package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Album;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
                () -> assertEquals(2, albums2.get(0).getArtist().getArtistId())
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
}
