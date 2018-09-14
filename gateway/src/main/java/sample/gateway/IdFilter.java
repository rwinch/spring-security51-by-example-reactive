package sample.gateway;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Rob Winch
 */
@Component
public class IdFilter implements WebFilter {

	public static final String USER_ID_HEADER_NAME = "user-id";

	public static final String USER_ID_CLAIM_NAME = "user_id";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange,
			WebFilterChain chain) {
		firewall(exchange);
		return exchange.getPrincipal()
			.cast(JwtAuthenticationToken.class)
			.map(JwtAuthenticationToken::getToken)
			.map(Jwt::getClaims)
			.filter(claims -> claims.containsKey(USER_ID_CLAIM_NAME))
			.map(claims -> claims.get(USER_ID_CLAIM_NAME))
			.cast(String.class)
			.map(userId -> withUserId(exchange, userId))
			.defaultIfEmpty(exchange)
			.flatMap(chain::filter);
	}

	private void firewall(ServerWebExchange exchange) {
		if (exchange.getRequest().getHeaders().containsKey(USER_ID_HEADER_NAME)) {
			throw new IllegalStateException("Malicious Client is trying to submit user-id header");
		}
	}

	private ServerWebExchange withUserId(ServerWebExchange exchange, String userId) {
		return exchange.mutate()
				.request(r -> r.header(USER_ID_HEADER_NAME, userId))
				.build();
	}
}
