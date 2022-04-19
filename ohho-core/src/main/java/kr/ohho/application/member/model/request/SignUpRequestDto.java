package kr.ohho.application.member.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequestDto {

    String email;
    String nickname;
    String password;
    String gender;
    String age;
    // TODO: 관심 있는 운동 추가로 입력
}
