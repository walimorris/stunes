package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Album;
import com.morris.stunes.model.Genre;
import com.morris.stunes.model.MediaType;
import com.morris.stunes.model.Track;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AuroraTrackRepositoryTests {

    @Autowired
    AuroraTrackRepository auroraTrackRepository;

    @Test
    public void findTrackByTrackId() {
        int trackId = 4;

        Track track = auroraTrackRepository.findTrackByTrackId(trackId);

        assertAll(
                () -> assertNotNull(track),
                () -> assertEquals(4, track.getTrackId()),
                () -> assertInstanceOf(Album.class, track.getAlbum()),
                () -> assertInstanceOf(Genre.class, track.getGenre()),
                () -> assertInstanceOf(MediaType.class, track.getMediaType())
        );
    }

    @Test
    public void findTracksByAlbumId() {
        int albumId = 3;

        List<Track> tracks = auroraTrackRepository.findTracksByAlbumId(albumId);

        assertAll(
                () -> assertNotNull(tracks),
                () -> assertEquals(3, tracks.size()),
                () -> assertEquals(3, tracks.get(0).getTrackId()),
                () -> assertEquals(4, tracks.get(1).getTrackId()),
                () -> assertEquals(5, tracks.get(2).getTrackId())
        );

        for (Track track : tracks) {
            assertAll(
                    () -> assertInstanceOf(Album.class, track.getAlbum()),
                    () -> assertInstanceOf(MediaType.class, track.getMediaType()),
                    () -> assertInstanceOf(Genre.class, track.getGenre())
            );
        }
    }
}
