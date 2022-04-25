package kr.ohho;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ohho.application.member.MemberService;
import kr.ohho.application.member.model.request.SignUpRequestDto;
import kr.ohho.application.member.model.response.SignUpResponseDto;
import kr.ohho.config.jwt.JwtService;
import kr.ohho.ui.member.MemberController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com") // (1)
public class MemberDocumentationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // (2)
    private MemberService memberService;

    @MockBean // (2)
    private JwtService jwtService;

    @MockBean // (2)
    private PasswordEncoder passwordEncoder;

    @Test

    public void signUp() throws Exception {

        //given
        SignUpResponseDto response = SignUpResponseDto.builder()
            .accessToken("1a2s3d4f")
            .build();

        when(memberService.signUp(any())).thenReturn(response);

        //when
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("test@email.com");
        signUpRequestDto.setNickname("테스트");
        signUpRequestDto.setPassword("test");
        signUpRequestDto.setGender("W");
        signUpRequestDto.setAge("10");

        ResultActions result = this.mockMvc.perform(
            post("/api/v1/members/signup")
                .content(objectMapper.writeValueAsString(signUpRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
            .andDo(document("member-sign-up", // (4)
                ApiDocumentUtil.getDocumentRequest(),
                ApiDocumentUtil.getDocumentResponse(),
                requestFields(
                    fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                    fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                    fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                    fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                    fieldWithPath("age").type(JsonFieldType.STRING).description("나이대")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                    fieldWithPath("data.accessToken").type(JsonFieldType.STRING)
                        .description("엑세스토큰")
                )
            ));
    }
}