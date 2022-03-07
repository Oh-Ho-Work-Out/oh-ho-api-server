package ohho.backend.spring.domain.member.vo;

import lombok.Value;
import ohho.backend.spring.domain.member.entities.Member;

@Value(staticConstructor = "of")
public class LoginResponseVo {

    String accessToken;
    Member member;
}
