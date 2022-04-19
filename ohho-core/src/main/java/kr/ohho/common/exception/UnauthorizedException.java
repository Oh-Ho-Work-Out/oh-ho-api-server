package kr.ohho.common.exception;

import kr.ohho.common.ResultCode;

public class UnauthorizedException extends OhhoServerException {

    public UnauthorizedException() {
        super(ResultCode.UNAUTHORIZED);
    }

    public UnauthorizedException(ResultCode resultCode) {
        super(resultCode);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(ResultCode.UNAUTHORIZED, message, cause);
    }
}
