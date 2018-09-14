package sample.message;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Rob Winch
 */
@RestController
@RequestMapping("/messages")
public class MessageController {
	private final MessageRepository messages;

	public MessageController(MessageRepository messages) {
		this.messages = messages;
	}

	@GetMapping("/inbox")
	Flux<Message> inbox() {
		String currentUserId = "1";
		return this.messages.findByTo(currentUserId);
	}

	@GetMapping("/{id}")
	Mono<Message> findById(@PathVariable Long id) {
		return this.messages.findById(id);
	}

	@DeleteMapping("/{id}")
	Mono<Void> deleteById(@PathVariable Long id) {
		return this.messages.deleteById(id);
	}
}
