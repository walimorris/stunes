package com.morris.stunes.repository;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Artist;
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

    @Test
    void testFindByNameIsLikeIgnoreCase() {
        String testQuery1 = "Ae";
        String testQuery2 = "ae";

        Pageable pageableRequest = PageRequest.of(0, 12);
        Page<Artist> artist1ResultsPageable = auroraArtistRepository.findByNameIsLikeIgnoreCase(testQuery1, pageableRequest);
        Page<Artist> artist2RequestPageable = auroraArtistRepository.findByNameIsLikeIgnoreCase(testQuery2, pageableRequest);

        assertAll(
                () -> assertEquals(artist1ResultsPageable.getTotalPages(), artist2RequestPageable.getTotalPages()),
                () -> assertEquals(artist1ResultsPageable.getTotalElements(), artist2RequestPageable.getTotalElements())
        );

        List<Artist> results1 = artist1ResultsPageable.getContent();
        List<Artist> results2 = artist2RequestPageable.getContent();

        assertEquals(results1.size(), results2.size());
        for (int i = 0; i < results1.size(); i++) {
            Artist artist1 = results1.get(i);
            Artist artist2 = results2.get(i);

            assertAll(
                    () -> assertEquals(artist1.getClass(), artist2.getClass()),
                    () -> assertEquals(artist1.getArtistId(), artist2.getArtistId()),
                    () -> assertEquals(artist1.getName(), artist2.getName())
            );
        }
    }
}
