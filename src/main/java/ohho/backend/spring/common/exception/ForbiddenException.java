package ohho.backend.spring.common.exception;


import ohho.backend.spring.common.ResultCode;

public class ForbiddenException extends OhhoServerException {

    public ForbiddenException() {
        super(ResultCode.FORBIDDEN);
    }

    public ForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }
}
