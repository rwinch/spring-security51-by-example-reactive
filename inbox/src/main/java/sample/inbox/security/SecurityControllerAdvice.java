package sample.inbox.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import reactor.core.publisher.Mono;

import java.security.Principal;
import sample.inbox.user.User;

/**
 * @author Rob Winch
 */
@ControllerAdvice
public class SecurityControllerAdvice {

	@ModelAttribute("currentUser")
	Mono<User> currentUser(@AuthenticationPrincipal Mono<User> currentUser) {
		return currentUser;
	}
}
