package ohho.backend.spring.domain.member.vo;

import lombok.Value;

@Value(staticConstructor = "of")
public class JoinRequestVo {

    String email;
    String nickname;
    String password;

    // TODO: 관심 있는 운동 추가로 입력
}
