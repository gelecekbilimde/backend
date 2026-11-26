package org.gelecekbilimde.scienceplatform;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

abstract class TestContainerConfiguration {

	private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgresql:11.1")
		.withUsername("postgres")
		.withPassword("postgres")
		.withDatabaseName("44125")
		.withCommand("--max-connections=1000");

	static {
		POSTGRE_SQL_CONTAINER.start();
	}

	@DynamicPropertySource
	static void overrideProps(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
		dynamicPropertyRegistry.add("spring.datasource.writer.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.reader.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
	}

}
