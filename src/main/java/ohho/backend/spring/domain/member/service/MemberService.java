package ohho.backend.spring.domain.member.service;

import java.util.Collection;
import java.util.List;
import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.vo.JoinRequestVo;

public interface MemberService {

    Member getMember(Long memberId);

    Member join(JoinRequestVo joinRequestVo);
}
