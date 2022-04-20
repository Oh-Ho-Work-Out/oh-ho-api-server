package kr.ohho.application.member.exception;

import kr.ohho.common.ResultCode;
import kr.ohho.common.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException() {
        super(ResultCode.MEMBER_NOT_FOUND);
    }
}

