package ohho.backend.spring.domain.member.controller;

import lombok.RequiredArgsConstructor;
import ohho.backend.spring.common.response.ApiResponse;
import ohho.backend.spring.domain.member.model.request.SignInRequestDto;
import ohho.backend.spring.domain.member.model.request.SignUpRequestDto;
import ohho.backend.spring.domain.member.model.response.SignInResponseDto;
import ohho.backend.spring.domain.member.model.response.SignUpResponseDto;
import ohho.backend.spring.domain.member.service.MemberService;
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

}
