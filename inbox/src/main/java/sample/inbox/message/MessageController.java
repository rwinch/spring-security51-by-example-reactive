package sample.inbox.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

/**
 * @author Rob Winch
 */
@Controller
@RequestMapping("/messages")
public class MessageController {
	private final MessageService messages;

	public MessageController(MessageService messages) {
		this.messages = messages;
	}

	@GetMapping("/inbox")
	Rendering inbox() {
		return Rendering.view("messages/inbox")
				.modelAttribute("messages", this.messages.inbox())
				.build();
	}

	@GetMapping("/{id}")
	Rendering message(@PathVariable String id) {
		return Rendering.view("messages/view")
				.modelAttribute("message", this.messages.findById(id))
				.build();
	}

	@DeleteMapping("/{id}")
	Mono<String> deleteById(@PathVariable String id) {
		return this.messages
			.deleteById(id)
			.thenReturn("redirect:/messages/inbox?deleted");
	}
}
