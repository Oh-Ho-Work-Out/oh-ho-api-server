package ohho.backend.spring.domain.member.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import ohho.backend.spring.domain.member.enums.Age;
import ohho.backend.spring.domain.member.enums.Gender;

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
