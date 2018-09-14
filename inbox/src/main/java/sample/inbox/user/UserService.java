package sample.inbox.user;

import reactor.core.publisher.Mono;

/**
 * @author Rob Winch
 */
public interface UserService {
	Mono<User> save(User user);
	Mono<User> findByEmail(String email);
	Mono<User> findById(String id);
}
