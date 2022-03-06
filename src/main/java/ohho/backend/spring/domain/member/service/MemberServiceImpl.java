package ohho.backend.spring.domain.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.exception.ApplicantNotFoundException;
import ohho.backend.spring.domain.member.repository.MemberRepository;
import ohho.backend.spring.domain.member.vo.JoinRequestVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member getMember(Long applicantId) {
        Assert.notNull(applicantId, "'applicantId' must not be null");
        return memberRepository.findById(applicantId)
            .orElseThrow(ApplicantNotFoundException::new);
    }

    @Transactional
    @Override
    public Member join(JoinRequestVo joinRequestVo) {
        Assert.notNull(joinRequestVo, "'joinRequestVo' must not be null");
        return memberRepository.findByEmail(joinRequestVo.getPassword())
            .orElseGet(() -> memberRepository.save(
                Member.of(joinRequestVo.getEmail(), joinRequestVo.getPassword()))
            );
    }
}
