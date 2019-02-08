package sample.message;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Rob Winch
 */
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal(expression = "claims['user_id']")
public @interface CurrentUserId {
}
