package kr.ohho.application.member;


import kr.ohho.application.member.model.request.SignInRequestDto;
import kr.ohho.application.member.model.request.SignInWithGoogleRequestDto;
import kr.ohho.application.member.model.request.SignUpRequestDto;
import kr.ohho.application.member.model.request.SignUpWithGoogleRequestDto;
import kr.ohho.application.member.model.response.GetMemberByNicknameResponseDto;
import kr.ohho.application.member.model.response.GetMyInfoResponseDto;
import kr.ohho.application.member.model.response.SignInResponseDto;
import kr.ohho.application.member.model.response.SignUpResponseDto;
import kr.ohho.domain.member.Member;

public interface MemberService {

    Member getMember(Long memberId);

    GetMemberByNicknameResponseDto getMemberByNickname(String nickname);

    GetMyInfoResponseDto getMyInfo(Long memberId);

    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    SignUpResponseDto signUpWithGoogle(SignUpWithGoogleRequestDto signUpWithGoogleRequestDto);

    SignInResponseDto signInWithGoogle(SignInWithGoogleRequestDto signInWithGoogleRequestDto);
}
