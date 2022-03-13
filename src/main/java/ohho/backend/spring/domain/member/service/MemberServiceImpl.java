package ohho.backend.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import ohho.backend.spring.config.jwt.JwtService;
import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.exception.MemberNicknameAlreadyExistException;
import ohho.backend.spring.domain.member.exception.MemberNotFoundException;
import ohho.backend.spring.domain.member.exception.MemberEmailDuplicatedException;
import ohho.backend.spring.domain.member.exception.MemberSignUpRequestInvalidException;
import ohho.backend.spring.domain.member.model.request.SignInRequestDto;
import ohho.backend.spring.domain.member.model.request.SignUpRequestDto;
import ohho.backend.spring.domain.member.model.response.GetMyInfoResponseDto;
import ohho.backend.spring.domain.member.model.response.GetMemberByNicknameDto;
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
            signUpRequestDto.getNickname(),
            signUpRequestDto.getGender(),
            signUpRequestDto.getAge()
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
        if (memberRepository.existsByNickname(signUpRequestDto.getNickname())) {
            throw new MemberNicknameAlreadyExistException(
                "이미 사용중인 nickname 입니다. nickname: " + signUpRequestDto.getNickname());
        }
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        Member existingMember = memberRepository.findByEmail(signInRequestDto.getEmail())
            .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(signInRequestDto.getPassword(),
            existingMember.getPassword())) {
            throw new MemberNotFoundException();
        }

        return new SignInResponseDto(jwtService.encode(existingMember.getId()));
    }

    @Override
    public GetMyInfoResponseDto getMyInfo(Long memberId) {
        Assert.notNull(memberId, "'memberId' must not be null");
        Member member = memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);

        return new GetMyInfoResponseDto(member.getId(), member.getEmail(), member.getNickname(),
            member.getInterestList(), member.getGender(), member.getAge());
    }

    @Override
    public Member getMember(Long memberId) {
        Assert.notNull(memberId, "'memberId' must not be null");
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public GetMemberByNicknameDto getMemberByNickname(String nickname) {
        Assert.notNull(nickname, "'nickname' must not be null");
        Member member = memberRepository.findByNickname(nickname)
            .orElseThrow(MemberNotFoundException::new);
        return new GetMemberByNicknameDto(member.getId(), member.getNickname());
    }
}
