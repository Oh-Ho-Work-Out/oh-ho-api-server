package ohho.backend.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import ohho.backend.spring.config.jwt.JwtService;
import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.exception.MemberNotFoundException;
import ohho.backend.spring.domain.member.exception.MemberEmailDuplicatedException;
import ohho.backend.spring.domain.member.exception.MemberSignUpRequestInvalidException;
import ohho.backend.spring.domain.member.repository.MemberRepository;
import ohho.backend.spring.domain.member.vo.JoinRequestVo;
import ohho.backend.spring.domain.member.vo.LoginRequestVo;
import ohho.backend.spring.domain.member.vo.LoginResponseVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Member signUp(JoinRequestVo joinRequestVo) {
        Assert.notNull(joinRequestVo, "'joinRequestVo' must not be null");
        validate(joinRequestVo);

        Member member = Member.of(
            joinRequestVo.getEmail(),
            passwordEncoder.encode(joinRequestVo.getPassword()),
            joinRequestVo.getNickname()
        );
        // TODO: 클라쪽에서 로그인 어떻게 처리할지 논의
        return memberRepository.save(member);
    }

    private void validate(JoinRequestVo joinRequestVo) {
        Assert.notNull(joinRequestVo, "'adminMemberVo' must not be null");

        if (!StringUtils.hasText(joinRequestVo.getEmail())) {
            throw new MemberSignUpRequestInvalidException(
                "'username' must not be null, empty or blank");
        }
        if (!StringUtils.hasText(joinRequestVo.getPassword())) {
            throw new MemberSignUpRequestInvalidException(
                "'password' must not be null, empty or blank");
        }
        if (!StringUtils.hasText(joinRequestVo.getNickname())) {
            throw new MemberSignUpRequestInvalidException(
                "'nickname' must not be null, empty or blank");
        }
        if (memberRepository.existsByEmail(joinRequestVo.getEmail())) {
            throw new MemberEmailDuplicatedException(
                "이미 사용중인 email 입니다. email: " + joinRequestVo.getEmail());
        }
    }

    @Override
    public LoginResponseVo signIn(LoginRequestVo loginRequestVo) {
        Member member = memberRepository.findByEmailAndPassword(loginRequestVo.getEmail(),
                passwordEncoder.encode(loginRequestVo.getPassword()))
            .orElseThrow(MemberNotFoundException::new);
        return LoginResponseVo.of(
            jwtService.encode(member.getId()),
            member
        );
    }

    @Override
    public Member getMember(Long memberId) {
        Assert.notNull(memberId, "'memberId' must not be null");
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }
}
