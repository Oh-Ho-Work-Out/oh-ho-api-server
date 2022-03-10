package ohho.backend.spring.common.exception;

import ohho.backend.spring.common.ResultCode;

public class ServiceUnavailableException extends OhhoServerException {

    protected ServiceUnavailableException(ResultCode resultCode) {
        super(resultCode);
    }
}
