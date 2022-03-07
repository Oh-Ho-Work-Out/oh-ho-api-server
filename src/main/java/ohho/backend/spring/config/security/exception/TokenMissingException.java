package ohho.backend.spring.config.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TokenMissingException extends UsernameNotFoundException {

    public TokenMissingException(String message) {
        super(message);
    }

    public TokenMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
