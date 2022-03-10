package ohho.backend.spring.domain.member.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import ohho.backend.spring.config.jwt.JwtService;
import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.exception.MemberNotFoundException;
import ohho.backend.spring.domain.member.exception.MemberEmailDuplicatedException;
import ohho.backend.spring.domain.member.exception.MemberSignUpRequestInvalidException;
import ohho.backend.spring.domain.member.model.request.SignInRequestDto;
import ohho.backend.spring.domain.member.model.request.SignUpRequestDto;
import ohho.backend.spring.domain.member.model.response.SignInResponseDto;
import ohho.backend.spring.domain.member.model.response.SignUpResponseDto;
import ohho.backend.spring.domain.member.repository.MemberRepository;
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
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        Assert.notNull(signUpRequestDto, "'joinRequestVo' must not be null");
        validate(signUpRequestDto);

        Member member = Member.of(
            signUpRequestDto.getEmail(),
            passwordEncoder.encode(signUpRequestDto.getPassword()),
            signUpRequestDto.getNickname()
        );
        memberRepository.save(member);

        return new SignUpResponseDto(jwtService.encode(member.getId()));
    }

    private void validate(SignUpRequestDto signUpRequestDto) {
        Assert.notNull(signUpRequestDto, "'adminMemberVo' must not be null");

        if (!StringUtils.hasText(signUpRequestDto.getEmail())) {
            throw new MemberSignUpRequestInvalidException(
                "'username' must not be null, empty or blank");
        }
        if (!StringUtils.hasText(signUpRequestDto.getPassword())) {
            throw new MemberSignUpRequestInvalidException(
                "'password' must not be null, empty or blank");
        }
        if (!StringUtils.hasText(signUpRequestDto.getNickname())) {
            throw new MemberSignUpRequestInvalidException(
                "'nickname' must not be null, empty or blank");
        }
        if (memberRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new MemberEmailDuplicatedException(
                "이미 사용중인 email 입니다. email: " + signUpRequestDto.getEmail());
        }
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        Optional<Member> existingMember = memberRepository.findByEmail(signInRequestDto.getEmail());
        if (!existingMember.isPresent()) {
            throw new MemberNotFoundException();
        }

        if (!passwordEncoder.matches(signInRequestDto.getPassword(),
            existingMember.get().getPassword())) {
            throw new MemberNotFoundException();
        }

        return new SignInResponseDto(jwtService.encode(existingMember.get().getId()));
    }

    @Override
    public Member getMember(Long memberId) {
        Assert.notNull(memberId, "'memberId' must not be null");
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }
}
