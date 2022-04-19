package kr.ohho.application.partner.exception;


import kr.ohho.common.ResultCode;
import kr.ohho.common.exception.NotFoundException;

public class PartnerNotFoundException extends NotFoundException {

    public PartnerNotFoundException() {
        super(ResultCode.PARTNER_NOT_FOUND);
    }
}

