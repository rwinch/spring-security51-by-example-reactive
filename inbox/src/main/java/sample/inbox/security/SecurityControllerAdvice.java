package sample.inbox.security;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * @author Rob Winch
 */
@ControllerAdvice
public class SecurityControllerAdvice {

	@ModelAttribute("currentUser")
	Mono<Principal> currentUser(Mono<Principal> currentUser) {
		return currentUser;
	}
}
