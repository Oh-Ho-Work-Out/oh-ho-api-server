package ohho.backend.spring.domain.member.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ohho.backend.spring.domain.member.enums.Age;
import ohho.backend.spring.domain.member.enums.Gender;

@Data
@AllArgsConstructor
public class GetMyInfoResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private String interestList;
    private Gender gender;
    private Age age;

    // TODO: partner, organization, exerciseHistories 추가
}
