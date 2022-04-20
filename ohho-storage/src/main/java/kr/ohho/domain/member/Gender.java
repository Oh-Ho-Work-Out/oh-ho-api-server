package kr.ohho.domain.member;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public enum Gender {
    WOMAN("여성", "W"),
    MAN("남성", "M");

    final String value;
    final String code;

    Gender(String value, String code) {
        this.value = value;
        this.code = code;
    }

    public static Gender ofGender(String code) {

        return Arrays.stream(Gender.values())
            .filter(v -> v.getCode().equals(code))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
