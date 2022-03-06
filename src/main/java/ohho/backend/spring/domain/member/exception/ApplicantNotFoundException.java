package ohho.backend.spring.domain.member.exception;

import ohho.backend.spring.common.ResultCode;
import ohho.backend.spring.common.exception.NotFoundException;

public class ApplicantNotFoundException extends NotFoundException {

    public ApplicantNotFoundException() {
        super(ResultCode.MEMBER_NOT_FOUND);
    }
}

