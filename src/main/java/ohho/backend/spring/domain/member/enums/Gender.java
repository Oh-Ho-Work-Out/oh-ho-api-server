package ohho.backend.spring.domain.member.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public enum Gender {
    WOMAN("W", "여성"),
    MAN("M", "남성");

    final String code;
    final String value;

    Gender(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Gender ofGender(String name) {

        return Arrays.stream(Gender.values())
            .filter(v -> v.getValue().equals(name))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
