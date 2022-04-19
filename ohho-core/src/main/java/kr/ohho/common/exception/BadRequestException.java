package kr.ohho.common.exception;

import kr.ohho.common.ResultCode;

public class BadRequestException extends OhhoServerException {

    public BadRequestException() {
        super(ResultCode.BAD_REQUEST);
    }

    public BadRequestException(ResultCode resultCode) {
        super(resultCode);
    }

    public BadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public BadRequestException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
