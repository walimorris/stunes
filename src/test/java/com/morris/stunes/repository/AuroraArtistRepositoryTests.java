package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Artist;
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
public class AuroraArtistRepositoryTests {

    @Autowired
    AuroraArtistRepository auroraArtistRepository;

    @Test
    public void saveArtist() {
        List<Artist> artistsPre = auroraArtistRepository.findAll();

        Artist artist = new Artist();
        artist.setName("Drake");

        auroraArtistRepository.save(artist);
        List<Artist> artists = auroraArtistRepository.findAll();

        assertAll(
                () -> assertTrue(artists.size() > artistsPre.size())
        );
    }

    @Test
    void testFindByName() {
        Artist artist1 = auroraArtistRepository.findByName("AC/DC");
        Artist artist2 = auroraArtistRepository.findByName("105 OR 1=1");

        assertAll(
                () -> assertEquals("AC/DC", artist1.getName()),
                () -> assertNull(artist2)
        );
    }

    @Test
    void testFindByNameLike() {
        String name = "Drake";
        List<Artist> artists = auroraArtistRepository.findByNameLike(name, name);
        assertTrue(artists.size() > 1);
    }
}
