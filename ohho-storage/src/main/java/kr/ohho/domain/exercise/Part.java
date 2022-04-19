package kr.ohho.domain.exercise;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public enum Part {
    LOWER("LOWER", "하체"),
    BACK("BACK", "등"),
    CHEST("CHEST", "가슴"),
    SHOULDER("SHOULDER", "어깨"),
    BICEPS("BICEPS", "이두"),
    TRICEPS("TRICEPS", "삼두"),
    ARM("ARM", "팔"); // 팔 (이두 + 삼두)

    final String part;
    final String name;

    Part(String part, String name) {
        this.part = part;
        this.name = name;
    }

    public static Part ofPart(String name) {

        return Arrays.stream(Part.values())
            .filter(v -> v.getName().equals(name))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
