package ohho.backend.spring.common.exception;

import ohho.backend.spring.common.ResultCode;

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
