package ohho.backend.spring.domain.member.service;

import ohho.backend.spring.domain.member.entities.Member;
import ohho.backend.spring.domain.member.model.request.SignInRequestDto;
import ohho.backend.spring.domain.member.model.request.SignUpRequestDto;
import ohho.backend.spring.domain.member.model.response.GetMyInfoResponseDto;
import ohho.backend.spring.domain.member.model.response.SignInResponseDto;
import ohho.backend.spring.domain.member.model.response.SignUpResponseDto;

public interface MemberService {

    Member getMember(Long memberId);

    GetMyInfoResponseDto getMyInfo(Long memberId);

    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);
}
