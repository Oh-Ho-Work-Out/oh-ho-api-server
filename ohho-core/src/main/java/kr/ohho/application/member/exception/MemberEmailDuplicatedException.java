package kr.ohho.application.member.exception;

import kr.ohho.common.ResultCode;
import kr.ohho.common.exception.BadRequestException;

public class MemberEmailDuplicatedException extends BadRequestException {

    public MemberEmailDuplicatedException(String message) {
        super(ResultCode.BAD_REQUEST, message);
    }
}
