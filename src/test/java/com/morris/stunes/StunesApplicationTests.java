package com.morris.stunes;

import com.morris.stunes.model.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StunesApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void saveArtist() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stunes");

		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();

			Artist artist = new Artist();
			artist.setName("Drake");

			entityManager.persist(artist);
			entityManager.getTransaction().commit();

			entityManager.getTransaction().begin();

			List<Artist> artists = entityManager.createQuery(
					"SELECT a FROM Artist a WHERE a.name = :artistName", Artist.class)
					.setParameter("artistName", "Drake-boy")
					.getResultList();

			artists.get(0).setName("Drake-boy");
			entityManager.getTransaction().commit();

			assertAll(
					() -> assertEquals(1, artists.size()),
					() -> assertEquals("Drake-boy", artists.get(0).getName())
			);
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}
}
