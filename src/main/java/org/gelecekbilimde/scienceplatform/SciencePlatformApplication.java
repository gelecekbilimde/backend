package org.gelecekbilimde.scienceplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
	@PropertySource("classpath:string.properties")
})
@SpringBootApplication
public class SciencePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(SciencePlatformApplication.class, args);
	}

}
