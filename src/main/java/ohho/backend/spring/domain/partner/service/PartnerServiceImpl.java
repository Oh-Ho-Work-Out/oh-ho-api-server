package ohho.backend.spring.domain.partner.service;

import lombok.RequiredArgsConstructor;

import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.exception.MemberNotFoundException;
import ohho.backend.spring.domain.member.repository.MemberRepository;
import ohho.backend.spring.domain.notification.service.NotificationService;
import ohho.backend.spring.domain.partner.entities.Partner;
import ohho.backend.spring.domain.partner.exception.PartnerNotFoundException;
import ohho.backend.spring.domain.partner.model.request.AcceptPartnerRequestDto;
import ohho.backend.spring.domain.partner.model.request.RequestPartnerRequestDto;
import ohho.backend.spring.domain.partner.model.request.UpdateDdayAndGoalRequestDto;
import ohho.backend.spring.domain.partner.repository.PartnerRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final MemberRepository memberRepository;
    private final NotificationService notificationService;

    @Override
    public String requestPartner(Long senderId,
        RequestPartnerRequestDto requestPartnerRequestDto) {
        notificationService.send(senderId, requestPartnerRequestDto.getReceiverId());
        return "파트너 요청이 성공적으로 완료되었습니다.";
    }

    @Override
    public Partner acceptPartner(Long requestId,
        AcceptPartnerRequestDto acceptPartnerRequestDto) {
        Member member1 = memberRepository.findById(requestId)
            .orElseThrow(MemberNotFoundException::new);
        Member member2 = memberRepository.findById(acceptPartnerRequestDto.getSenderId())
            .orElseThrow(MemberNotFoundException::new);
        Partner partner = new Partner();
        partner.addMember(member1);
        partner.addMember(member2);
        partnerRepository.save(partner);
        return partner;
    }

    @Override
    public Partner getPartner(Long partnerId) {
        Assert.notNull(partnerId, "'partnerId' must not be null");
        return partnerRepository.findById(partnerId)
            .orElseThrow(PartnerNotFoundException::new);
    }

    @Override
    @Transactional
    public String updateDdayAndGoal(Long id, UpdateDdayAndGoalRequestDto updateDdayAndGoalRequest) {
        partnerRepository.updateDdayAndGoal(id, updateDdayAndGoalRequest.getDDay(),
            updateDdayAndGoalRequest.getGoal());
        return "업데이트가 완료되었습니다.";
    }
}
