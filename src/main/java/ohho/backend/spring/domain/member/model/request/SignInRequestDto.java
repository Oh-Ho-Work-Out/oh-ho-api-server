package ohho.backend.spring.domain.member.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequestDto {

    String email;
    String password;
}
