package ohho.backend.spring.domain.member.vo;

import lombok.Value;

@Value(staticConstructor = "of")
public class LoginRequestVo {

    String email;
    String password;
}
