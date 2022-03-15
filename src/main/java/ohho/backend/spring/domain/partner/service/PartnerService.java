package ohho.backend.spring.domain.partner.service;

import ohho.backend.spring.domain.partner.entities.Partner;
import ohho.backend.spring.domain.partner.model.request.AcceptPartnerRequestDto;
import ohho.backend.spring.domain.partner.model.request.RequestPartnerRequestDto;
import ohho.backend.spring.domain.partner.model.request.UpdateDdayAndGoalRequestDto;

public interface PartnerService {

    String requestPartner(Long senderId, RequestPartnerRequestDto requestPartnerRequestDto);

    Partner acceptPartner(Long requestId, AcceptPartnerRequestDto acceptPartnerRequestDto);

    Partner getPartner(Long partnerId);

    String updateDdayAndGoal(Long id, UpdateDdayAndGoalRequestDto updateDdayAndGoalRequest);
}
