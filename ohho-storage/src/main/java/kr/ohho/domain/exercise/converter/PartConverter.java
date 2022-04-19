package kr.ohho.domain.exercise.converter;

import javax.persistence.AttributeConverter;
import kr.ohho.domain.exercise.Part;

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
