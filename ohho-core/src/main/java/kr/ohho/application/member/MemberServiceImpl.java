package kr.ohho.application.member;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import kr.ohho.application.member.exception.MemberEmailDuplicatedException;
import kr.ohho.application.member.exception.MemberNicknameAlreadyExistException;
import kr.ohho.application.member.exception.MemberNotFoundException;
import kr.ohho.application.member.exception.MemberSignUpRequestInvalidException;
import kr.ohho.application.member.model.request.SignInRequestDto;
import kr.ohho.application.member.model.request.SignInWithGoogleRequestDto;
import kr.ohho.application.member.model.request.SignUpRequestDto;
import kr.ohho.application.member.model.request.SignUpWithGoogleRequestDto;
import kr.ohho.application.member.model.response.GetMemberByNicknameResponseDto;
import kr.ohho.application.member.model.response.GetMyInfoResponseDto;
import kr.ohho.application.member.model.response.SignInResponseDto;
import kr.ohho.application.member.model.response.SignUpResponseDto;
import kr.ohho.common.exception.UnauthorizedException;
import kr.ohho.config.jwt.JwtService;
import kr.ohho.domain.member.Member;
import kr.ohho.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${oauth.google.client.id}")
    private String clientId;

    @Override
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        Assert.notNull(signUpRequestDto, "'joinRequestVo' must not be null");
        validate(signUpRequestDto);

        Member member = Member.of(
            signUpRequestDto.getEmail(),
            passwordEncoder.encode(signUpRequestDto.getPassword()),
            null,
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
    public GetMemberByNicknameResponseDto getMemberByNickname(String nickname) {
        Assert.notNull(nickname, "'nickname' must not be null");
        Member member = memberRepository.findByNickname(nickname)
            .orElseThrow(MemberNotFoundException::new);
        return new GetMemberByNicknameResponseDto(member.getId(), member.getNickname());
    }

    @Override
    public SignUpResponseDto signUpWithGoogle(
        SignUpWithGoogleRequestDto signUpWithGoogleRequestDto) {
        Assert.notNull(signUpWithGoogleRequestDto, "'signUpWithGoogleRequestDto' must not be null");

        GoogleIdToken googleIdToken = verifyToken(
            signUpWithGoogleRequestDto.getGoogleIdToken()
        );
        String googleUserId = googleIdToken.getPayload().getSubject();

        Member member =
            memberRepository.findByGoogleUserId(signUpWithGoogleRequestDto.getGoogleIdToken())
                .orElseGet(() -> memberRepository.save(
                    Member.of(null, null, googleUserId,
                        signUpWithGoogleRequestDto.getNickname(),
                        signUpWithGoogleRequestDto.getGender(),
                        signUpWithGoogleRequestDto.getAge())
                ));
        return new SignUpResponseDto(jwtService.encode(member.getId()));
    }

    private GoogleIdToken verifyToken(String googleIdToken) {
        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(
            new NetHttpTransport(),
            GsonFactory.getDefaultInstance())
            .setAudience(Collections.singletonList(clientId))
            .build();
        try {
            return googleIdTokenVerifier.verify(googleIdToken);
        } catch (GeneralSecurityException | IOException e) {
            log.error("Failed to verify token", e);
            throw new UnauthorizedException("Failed to verify google id token", e);
        }
    }

    @Override
    public SignInResponseDto signInWithGoogle(
        SignInWithGoogleRequestDto signInWithGoogleRequestDto) {
        Assert.notNull(signInWithGoogleRequestDto, "'signInWithGoogleRequestDto' must not be null");
        Member member =
            memberRepository.findByGoogleUserId(signInWithGoogleRequestDto.getGoogleIdToken())
                .orElseThrow(MemberNotFoundException::new);
        return new SignInResponseDto(jwtService.encode(member.getId()));
    }
}
