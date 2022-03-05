package ohho.backend.spring.domain.exercise.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public enum ExerciseType {
    CLIMBING("CLIMBING", "암벽등반"),
    FITNESS("FITNESS", "헬스"),
    RUNNING("RUNNING", "러닝");

    final String type;
    final String name;

    ExerciseType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public static ExerciseType ofExerciseType(String name) {

        return Arrays.stream(ExerciseType.values())
            .filter(v -> v.getName().equals(name))
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}
