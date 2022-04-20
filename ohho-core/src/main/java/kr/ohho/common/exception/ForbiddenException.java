package kr.ohho.common.exception;


import kr.ohho.common.ResultCode;

public class ForbiddenException extends OhhoServerException {

    public ForbiddenException() {
        super(ResultCode.FORBIDDEN);
    }

    public ForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }
}
