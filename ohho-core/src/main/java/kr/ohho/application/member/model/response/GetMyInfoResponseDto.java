package kr.ohho.application.member.model.response;

import kr.ohho.domain.member.Age;
import kr.ohho.domain.member.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

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
