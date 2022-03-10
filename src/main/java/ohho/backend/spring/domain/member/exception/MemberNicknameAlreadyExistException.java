package ohho.backend.spring.domain.member.exception;

public class MemberNicknameAlreadyExistException extends RuntimeException {

    public MemberNicknameAlreadyExistException(String message) {
        super(message);
    }

}
