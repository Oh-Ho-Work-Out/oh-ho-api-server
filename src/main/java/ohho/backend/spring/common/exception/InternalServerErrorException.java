package ohho.backend.spring.common.exception;

import ohho.backend.spring.common.ResultCode;

public class InternalServerErrorException extends OhhoServerException {

    protected InternalServerErrorException() {
        super(ResultCode.INTERNAL_SERVER_ERROR);
    }

    protected InternalServerErrorException(ResultCode resultCode) {
        super(resultCode);
    }
}
