package kr.ohho.application.partner;

import kr.ohho.application.member.exception.MemberNotFoundException;
import kr.ohho.application.notification.NotificationService;
import kr.ohho.application.partner.exception.PartnerNotFoundException;
import kr.ohho.application.partner.model.request.AcceptPartnerRequestDto;
import kr.ohho.application.partner.model.request.RequestPartnerRequestDto;
import kr.ohho.application.partner.model.request.UpdateDdayAndGoalRequestDto;
import kr.ohho.domain.exercise.Part;
import kr.ohho.domain.member.Member;
import kr.ohho.domain.member.repository.MemberRepository;
import kr.ohho.domain.partner.Partner;
import kr.ohho.domain.partner.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final MemberRepository memberRepository;
    private final NotificationService notificationService;

    public String requestPartner(Long senderId,
        RequestPartnerRequestDto requestPartnerRequestDto) {
        notificationService.send(senderId, requestPartnerRequestDto.getReceiverId());
        return "파트너 요청이 성공적으로 완료되었습니다.";
    }
    
    public String acceptPartner(Long requestId,
        AcceptPartnerRequestDto acceptPartnerRequestDto) {
        Member member1 = memberRepository.findById(requestId)
            .orElseThrow(MemberNotFoundException::new);
        Member member2 = memberRepository.findById(acceptPartnerRequestDto.getSenderId())
            .orElseThrow(MemberNotFoundException::new);
        Partner partner = new Partner();
        partner.addMember(member1);
        partner.addMember(member2);
        partnerRepository.save(partner);
        return "파트너가 성공적으로 생성되었습니다.";
    }

    public Long getPartner(Long partnerId) {
        Assert.notNull(partnerId, "'partnerId' must not be null");
        Partner partner = partnerRepository.findById(partnerId)
            .orElseThrow(PartnerNotFoundException::new);
        return partner.getId();
    }

//   
//    @Transactional
//    public String updateDdayAndGoal(Long id, UpdateDdayAndGoalRequestDto updateDdayAndGoalRequest) {
//        partnerRepository.updateDdayAndGoal(id, updateDdayAndGoalRequest.getDDay(),
//            updateDdayAndGoalRequest.getGoal());
//        return "업데이트가 완료되었습니다.";
//    }
}
