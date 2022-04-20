package kr.ohho.application.partner;


import kr.ohho.application.partner.model.request.AcceptPartnerRequestDto;
import kr.ohho.application.partner.model.request.RequestPartnerRequestDto;
import kr.ohho.application.partner.model.request.UpdateDdayAndGoalRequestDto;
import kr.ohho.domain.partner.Partner;

public interface PartnerService {

    String requestPartner(Long senderId, RequestPartnerRequestDto requestPartnerRequestDto);

    String acceptPartner(Long requestId, AcceptPartnerRequestDto acceptPartnerRequestDto);

    Long getPartner(Long partnerId);

//    String updateDdayAndGoal(Long id, UpdateDdayAndGoalRequestDto updateDdayAndGoalRequest);
}
