package sample.inbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class InboxApplication {

	@Bean
	WebClient webClient() {
		return WebClient.create();
	}

	public static void main(String[] args) {
		SpringApplication.run(InboxApplication.class, args);
	}
}
