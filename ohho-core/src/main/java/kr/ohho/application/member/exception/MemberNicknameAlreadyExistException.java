package kr.ohho.application.member.exception;

public class MemberNicknameAlreadyExistException extends RuntimeException {

    public MemberNicknameAlreadyExistException(String message) {
        super(message);
    }

}
