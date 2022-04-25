package kr.ohho.application.member.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
@Builder
public class SignUpResponseDto {

    private String accessToken;
}
