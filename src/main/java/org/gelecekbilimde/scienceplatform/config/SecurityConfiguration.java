package org.gelecekbilimde.scienceplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	// todo jwt auth la birlikte değişmeli
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.csrf()
			.disable()
			.exceptionHandling()
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/version")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();


		return http.build();
	}

}
