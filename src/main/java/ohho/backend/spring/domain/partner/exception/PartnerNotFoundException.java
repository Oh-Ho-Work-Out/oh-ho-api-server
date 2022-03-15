package ohho.backend.spring.domain.partner.exception;

import ohho.backend.spring.common.ResultCode;
import ohho.backend.spring.common.exception.NotFoundException;

public class PartnerNotFoundException extends NotFoundException {

    public PartnerNotFoundException() {
        super(ResultCode.PARTNER_NOT_FOUND);
    }
}

