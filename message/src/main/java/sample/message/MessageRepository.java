package sample.message;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Rob Winch
 */
public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
	Flux<Message> findByTo(String id);

	Mono<Message> findById(Long id);
}
