package kr.ohho.common.exception;

import kr.ohho.common.ResultCode;

public class InternalServerErrorException extends OhhoServerException {

    protected InternalServerErrorException() {
        super(ResultCode.INTERNAL_SERVER_ERROR);
    }

    protected InternalServerErrorException(ResultCode resultCode) {
        super(resultCode);
    }
}
