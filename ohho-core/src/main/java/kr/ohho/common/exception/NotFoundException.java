package kr.ohho.common.exception;

import kr.ohho.common.ResultCode;

public class NotFoundException extends OhhoServerException {

    public NotFoundException() {
        super(ResultCode.NOT_FOUND);
    }

    public NotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public NotFoundException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

}
