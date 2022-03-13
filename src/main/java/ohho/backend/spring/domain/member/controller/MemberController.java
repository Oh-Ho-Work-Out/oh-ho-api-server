package ohho.backend.spring.domain.member.controller;

import lombok.RequiredArgsConstructor;
import ohho.backend.spring.common.ApiResponse;
import ohho.backend.spring.domain.member.model.request.SignInRequestDto;
import ohho.backend.spring.domain.member.model.request.SignUpRequestDto;
import ohho.backend.spring.domain.member.model.response.GetMemberByNicknameDto;
import ohho.backend.spring.domain.member.model.response.GetMyInfoResponseDto;
import ohho.backend.spring.domain.member.model.response.SignInResponseDto;
import ohho.backend.spring.domain.member.model.response.SignUpResponseDto;
import ohho.backend.spring.domain.member.service.MemberService;
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
    public ApiResponse<SignInResponseDto> signUp(@RequestBody SignInRequestDto signInRequestDto) {
        return ApiResponse.success(memberService.signIn(signInRequestDto));
    }

    @GetMapping("/me")
    public ApiResponse<GetMyInfoResponseDto> getMyInfo(
        @ModelAttribute("memberId") Long memberId) {
        return ApiResponse.success(memberService.getMyInfo(memberId));
    }

    @GetMapping("/nickname/{nickname}")
    public ApiResponse<GetMemberByNicknameDto> searchMemberByNickname(
        @PathVariable("nickname") String nickname) {
        return ApiResponse.success(memberService.getMemberByNickname(nickname));
    }
}
