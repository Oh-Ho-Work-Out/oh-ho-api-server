package ohho.backend.spring.domain.member.enums.converter;

import javax.persistence.AttributeConverter;
import ohho.backend.spring.domain.member.enums.Gender;

public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.ofGender(dbData);
    }
}
