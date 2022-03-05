package ohho.backend.spring.domain.exercise.enums.converter;

import javax.persistence.AttributeConverter;
import ohho.backend.spring.domain.exercise.enums.Part;

public class PartConverter implements AttributeConverter<Part, String> {

    @Override
    public String convertToDatabaseColumn(Part attribute) {
        return attribute.getName();
    }

    @Override
    public Part convertToEntityAttribute(String dbData) {
        return Part.ofPart(dbData);
    }
}
