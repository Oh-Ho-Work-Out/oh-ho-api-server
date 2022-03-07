package ohho.backend.spring.domain.member.service;

import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.vo.JoinRequestVo;
import ohho.backend.spring.domain.member.vo.LoginRequestVo;
import ohho.backend.spring.domain.member.vo.LoginResponseVo;

public interface MemberService {

    Member getMember(Long memberId);

    Member signUp(JoinRequestVo joinRequestVo);

    LoginResponseVo signIn(LoginRequestVo loginRequestVo);
}
