package ohho.backend.spring.domain.partner.service;

import ohho.backend.spring.domain.partner.entities.Partner;
import ohho.backend.spring.domain.partner.model.request.UpdateDdayAndGoalRequest;

public interface PartnerService {

    Partner getPartner(Long partnerId);

    String updateDdayAndGoal(Long id, UpdateDdayAndGoalRequest updateDdayAndGoalRequest);
}
