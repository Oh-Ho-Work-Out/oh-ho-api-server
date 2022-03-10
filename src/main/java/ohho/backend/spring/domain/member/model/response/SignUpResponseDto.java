package ohho.backend.spring.domain.member.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class SignUpResponseDto {

    private String accessToken;
}
