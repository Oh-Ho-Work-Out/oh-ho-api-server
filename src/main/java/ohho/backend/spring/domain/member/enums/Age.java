package ohho.backend.spring.domain.member.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public enum Age {
    TEN("10대", "10"),
    TWENTY("20대", "20"),
    Thirty("30대", "30"),
    FORTY("40대", "40"),
    FIFTY("50대", "50"),
    SIXTY("60대", "60");

    final String value;
    final String code;

    Age(String value, String code) {
        this.value = value;
        this.code = code;
    }

    public static Age ofAge(String code) {

        return Arrays.stream(Age.values())
            .filter(v -> v.getCode().equals(code))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
