package org.gelecekbilimde.scienceplatform.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(
		contact = @Contact(
			name = "Gelecek Bilimde",
			email = "info@gelecekbilimde.net"
		),
		description = "Swagger documentation for Science Platform Application"
	),
	servers = {
		@Server(
			description = "local environment",
			url = "http://localhost:8080/api/"
		)
	},
	security = @SecurityRequirement(
		name = "bearerAuthentication"
	)
	)
@SecurityScheme(
	name = "bearerAuthentication",
	description = "JWT Token Authentication",
	scheme = "bearer",
	type = SecuritySchemeType.DEFAULT, // sorulacak
	bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER
)
public class SwaggerConfiguration {
}
