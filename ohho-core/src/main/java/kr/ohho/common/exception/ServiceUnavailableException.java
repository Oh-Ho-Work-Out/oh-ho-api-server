package kr.ohho.common.exception;

import kr.ohho.common.ResultCode;

public class ServiceUnavailableException extends OhhoServerException {

    protected ServiceUnavailableException(ResultCode resultCode) {
        super(resultCode);
    }
}
