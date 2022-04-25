package kr.ohho.ui.member;

import kr.ohho.application.member.MemberService;
import kr.ohho.application.member.model.request.SignInRequestDto;
import kr.ohho.application.member.model.request.SignInWithGoogleRequestDto;
import kr.ohho.application.member.model.request.SignUpRequestDto;
import kr.ohho.application.member.model.request.SignUpWithGoogleRequestDto;
import kr.ohho.application.member.model.response.GetMemberByNicknameResponseDto;
import kr.ohho.application.member.model.response.GetMyInfoResponseDto;
import kr.ohho.application.member.model.response.SignInResponseDto;
import kr.ohho.application.member.model.response.SignUpResponseDto;
import kr.ohho.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ApiResponse.success(memberService.signUp(signUpRequestDto));
    }

    @PostMapping("/signin")
성    public ApiResponse<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ApiResponse.success(memberService.signIn(signInRequestDto));
    }

    @PostMapping("/signup/google")
    public ApiResponse<SignUpResponseDto> signUpWithGoogle(
        @RequestBody SignUpWithGoogleRequestDto signUpWithGoogleRequestDto) {
        return ApiResponse.success(memberService.signUpWithGoogle(signUpWithGoogleRequestDto));
    }

    @PostMapping("/signin/google")
    public ApiResponse<SignInResponseDto> signInWithGoogle(
        @RequestBody SignInWithGoogleRequestDto signInWithGoogleRequestDto) {
        return ApiResponse.success(memberService.signInWithGoogle(signInWithGoogleRequestDto));
    }

    @GetMapping("/me")
    public ApiResponse<GetMyInfoResponseDto> getMyInfo(
        @ModelAttribute("memberId") Long memberId) {
        return ApiResponse.success(memberService.getMyInfo(memberId));
    }

    @GetMapping("/nickname/{nickname}")
    public ApiResponse<GetMemberByNicknameResponseDto> searchMemberByNickname(
        @PathVariable("nickname") String nickname) {
        return ApiResponse.success(memberService.getMemberByNickname(nickname));
    }
}
