package ohho.backend.spring.domain.member.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public enum Age {
    TEN("10", "TEN"),
    TWENTY("20", "TWENTY"),
    Thirty("30", "Thirty"),
    FORTY("40", "FORTY"),
    FIFTY("50", "FIFTY"),
    SIXTY("60", "SIXTY");

    final String number;
    final String value;

    Age(String number, String value) {
        this.number = number;
        this.value = value;
    }

    public static Age ofAge(String value) {

        return Arrays.stream(Age.values())
            .filter(v -> v.getValue().equals(value))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
