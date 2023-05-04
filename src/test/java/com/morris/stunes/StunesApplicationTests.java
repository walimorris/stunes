package com.morris.stunes;

import com.morris.stunes.configuration.JsonConfiguration;
import com.morris.stunes.configuration.Properties;
import com.morris.stunes.configuration.SpringDataConfiguration;
import com.morris.stunes.controller.TracksController;
import com.morris.stunes.controller.PerformanceController;
import com.morris.stunes.controller.HomeController;
import com.morris.stunes.controller.AlbumsController;
import com.morris.stunes.controller.ArtistsController;
import com.morris.stunes.repository.AuroraTrackRepository;
import com.morris.stunes.repository.AuroraInvoiceRepository;
import com.morris.stunes.repository.AuroraEmployeeRepository;
import com.morris.stunes.repository.AuroraCustomerRepository;
import com.morris.stunes.repository.AuroraArtistRepository;
import com.morris.stunes.repository.AuroraAlbumRepository;
import com.morris.stunes.service.impl.PageableServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
class StunesApplicationTests {

	@Autowired
	AuroraArtistRepository auroraArtistRepository;

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertNotNull(applicationContext);

		/*
		 Test Controller and Service component application context loads.
		 */
		assertAll(
				() -> assertNotNull(applicationContext.getBean(AlbumsController.class)),
				() -> assertNotNull(applicationContext.getBean(ArtistsController.class)),
				() -> assertNotNull(applicationContext.getBean(HomeController.class)),
				() -> assertNotNull(applicationContext.getBean(PerformanceController.class)),
				() -> assertNotNull(applicationContext.getBean(TracksController.class)),
				() -> assertNotNull(applicationContext.getBean(PageableServiceImpl.class))
		);

		/*
		 Test custom configuration and DAO applications context loads. Spring Data JPA creates proxy
		 class implementations for repository interface for application without needing to create
		 implementation classes explicitly.
		 */
		assertAll(
				() -> assertNotNull(applicationContext.getBean(JsonConfiguration.class)),
				() -> assertNotNull(applicationContext.getBean(Properties.class)),
				() -> assertNotNull(applicationContext.getBean(SpringDataConfiguration.class)),
				() -> assertNotNull(applicationContext.getBean(AuroraArtistRepository.class)),
				() -> assertNotNull(applicationContext.getBean(AuroraAlbumRepository.class)),
				() -> assertNotNull(applicationContext.getBean(AuroraCustomerRepository.class)),
				() -> assertNotNull(applicationContext.getBean(AuroraEmployeeRepository.class)),
				() -> assertNotNull(applicationContext.getBean(AuroraInvoiceRepository.class)),
				() -> assertNotNull(applicationContext.getBean(AuroraTrackRepository.class))
		);
	}
}
