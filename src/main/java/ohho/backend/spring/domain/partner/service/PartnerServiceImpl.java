package ohho.backend.spring.domain.partner.service;

import lombok.RequiredArgsConstructor;

import ohho.backend.spring.domain.partner.entities.Partner;
import ohho.backend.spring.domain.partner.exception.PartnerNotFoundException;
import ohho.backend.spring.domain.partner.model.request.UpdateDdayAndGoalRequest;
import ohho.backend.spring.domain.partner.repository.PartnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(Long partnerId) {
        Assert.notNull(partnerId, "'partnerId' must not be null");
        return partnerRepository.findById(partnerId)
            .orElseThrow(PartnerNotFoundException::new);
    }

    @Override
    @Transactional
    public String updateDdayAndGoal(Long id, UpdateDdayAndGoalRequest updateDdayAndGoalRequest) {
        partnerRepository.updateDdayAndGoal(id, updateDdayAndGoalRequest.getDDay(),
            updateDdayAndGoalRequest.getGoal());
        return "업데이트가 완료되었습니다.";
    }
}
