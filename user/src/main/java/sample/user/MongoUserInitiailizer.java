package sample.user;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * @author Rob Winch
 */
@Component
class MongoUserInitiailizer implements SmartInitializingSingleton {
	private final UserRepository users;

	MongoUserInitiailizer(UserRepository users) {
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		// sha256 w/ salt encoded "password"
		String passsword = "73ac8218b92f7494366bf3a03c0c2ee2095d0c03a29cb34c95da327c7aa17173248af74d46ba2d4c";

		this.users.save(new User(1L, "rob@example.com", passsword, "Rob", "Winch")).block();
		this.users.save(new User(2L, "joe@example.com", passsword, "Joe", "Grandja")).block();
		this.users.save(new User(3L, "josh@example.com", passsword, "Josh", "Cummings")).block();
	}
}
