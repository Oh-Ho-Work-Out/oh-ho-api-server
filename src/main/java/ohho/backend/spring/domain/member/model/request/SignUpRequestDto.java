package ohho.backend.spring.domain.member.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequestDto {

    String email;
    String nickname;
    String password;
    // TODO: 관심 있는 운동 추가로 입력
}
