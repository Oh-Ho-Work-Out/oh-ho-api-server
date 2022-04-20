package kr.ohho.application.member.exception;

import kr.ohho.common.ResultCode;
import kr.ohho.common.exception.BadRequestException;

public class MemberSignUpRequestInvalidException extends BadRequestException {

    public MemberSignUpRequestInvalidException(String message) {
        super(ResultCode.BAD_REQUEST, message);
    }
}
