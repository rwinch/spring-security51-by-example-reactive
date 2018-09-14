package sample.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Rob Winch
 */
@RestController
@RequestMapping(path="/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
	private final Random random = new SecureRandom();

	private final UserRepository users;

	public UserController(UserRepository users) {
		this.users = users;
	}

	@GetMapping
	Flux<User> users() {
		return this.users.findAll();
	}

	@GetMapping("/{id}")
	Mono<User> findById(@PathVariable Long id) {
		return this.users.findById(id);
	}

	@GetMapping(params = "email")
	Mono<User> findByEmail(@RequestParam String email) {
		return this.users.findByEmail(email);
	}

	@PostMapping
	Mono<User> save(@Valid @RequestBody User user) {
		return Mono.justOrEmpty(user)
			.doOnSuccess(u -> {
				if (u.getId() == null) {
					u.setId(this.random.nextLong());
				}
			})
			.publishOn(Schedulers.parallel())
			.flatMap(this.users::save);
	}
}
