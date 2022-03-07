package ohho.backend.spring.domain.member.exception;

public class MemberEmailDuplicatedException extends RuntimeException {

    public MemberEmailDuplicatedException(String message) {
        super(message);
    }
}
