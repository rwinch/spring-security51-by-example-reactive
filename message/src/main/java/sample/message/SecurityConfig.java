package sample.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author Rob Winch
 */
@Configuration
public class SecurityConfig {
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		// @formatter:off
		http
			.authorizeExchange()
				.anyExchange().hasAuthority("SCOPE_message:read")
				.and()
			.oauth2ResourceServer()
				.jwt();
		return http.build();
		// @formatter:on
	}
}
