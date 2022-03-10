package ohho.backend.spring.domain.member.exception;

import ohho.backend.spring.common.ResultCode;
import ohho.backend.spring.common.exception.BadRequestException;

public class MemberEmailDuplicatedException extends BadRequestException {

    public MemberEmailDuplicatedException(String message) {
        super(ResultCode.BAD_REQUEST, message);
    }
}
