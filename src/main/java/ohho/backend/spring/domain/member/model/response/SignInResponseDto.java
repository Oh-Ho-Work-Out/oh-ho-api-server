package ohho.backend.spring.domain.member.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ohho.backend.spring.domain.organization.entities.Organization;
import ohho.backend.spring.domain.partner.entities.Partner;

@Data
@AllArgsConstructor
public class SignInResponseDto {

    private String accessToken;
}
