package com.morris.stunes;

import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.model.Artist;
import com.morris.stunes.repository.AuroraArtistRepository;
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
class StunesApplicationTests {

	@Autowired
	AuroraArtistRepository auroraArtistRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void saveArtist() {
		List<Artist> artistsPre = (List<Artist>) auroraArtistRepository.findAll();

		Artist artist = new Artist();
		artist.setName("Drake");

		auroraArtistRepository.save(artist);
		List<Artist> artists = (List<Artist>) auroraArtistRepository.findAll();

		assertAll(
				() -> assertTrue(artists.size() > artistsPre.size())
		);
	}
}
