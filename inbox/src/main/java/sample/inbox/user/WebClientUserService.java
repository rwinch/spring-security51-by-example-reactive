package sample.inbox.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Rob Winch
 */
@Component
public class WebClientUserService implements UserService {
	private final WebClient webClient;

	private final String usersUrl;

	public WebClientUserService(WebClient webClient,
			@Value("${users-url}") String usersUrl) {
		this.webClient = webClient;
		this.usersUrl = usersUrl;
	}

	public Mono<User> save(User user) {
		return this.webClient.post()
				.uri(this.usersUrl)
				.syncBody(user)
				.retrieve()
				.bodyToMono(User.class);
	}

	public Mono<User> findByEmail(String email) {
		return this.webClient.get()
				.uri(this.usersUrl + "/?email={email}", email)
				.retrieve()
				.bodyToMono(User.class);
	}

	public Mono<User> findById(String id) {
		return this.webClient.get()
				.uri(this.usersUrl + "/{id}", id)
				.retrieve()
				.bodyToMono(User.class);
	}
}
