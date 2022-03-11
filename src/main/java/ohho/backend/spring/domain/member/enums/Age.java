package ohho.backend.spring.domain.member.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public enum Age {
    TEN("10", "10대"),
    TWENTY("20", "20대"),
    Thirty("30", "30대"),
    FORTY("40", "40대"),
    FIFTY("50", "50대"),
    SIXTY("60", "60대");

    final String age;
    final String value;

    Age(String age, String value) {
        this.age = age;
        this.value = value;
    }

    public static Age ofAge(String value) {

        return Arrays.stream(Age.values())
            .filter(v -> v.getValue().equals(value))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
