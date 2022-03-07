package ohho.backend.spring.common.exception;

import ohho.backend.spring.common.ResultCode;

public abstract class OhhoServerException extends RuntimeException {

    private final ResultCode resultCode;

    protected OhhoServerException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    protected OhhoServerException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    protected OhhoServerException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    protected OhhoServerException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
