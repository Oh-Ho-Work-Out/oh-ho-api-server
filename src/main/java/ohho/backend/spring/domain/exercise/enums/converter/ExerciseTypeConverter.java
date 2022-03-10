package ohho.backend.spring.domain.exercise.enums.converter;

import javax.persistence.AttributeConverter;
import ohho.backend.spring.domain.exercise.enums.ExerciseType;

public class ExerciseTypeConverter implements AttributeConverter<ExerciseType, String> {

    @Override
    public String convertToDatabaseColumn(ExerciseType attribute) {
        return attribute.getName();
    }

    @Override
    public ExerciseType convertToEntityAttribute(String dbData) {
        return ExerciseType.ofExerciseType(dbData);
    }
}
