package ohho.backend.spring.domain.member.exception;

public class MemberSignUpRequestInvalidException extends RuntimeException {

    public MemberSignUpRequestInvalidException(String message) {
        super(message);
    }
}
