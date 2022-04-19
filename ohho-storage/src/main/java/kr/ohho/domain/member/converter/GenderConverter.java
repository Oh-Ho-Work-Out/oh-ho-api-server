package kr.ohho.domain.member.converter;

import javax.persistence.AttributeConverter;
import kr.ohho.domain.member.Gender;

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
