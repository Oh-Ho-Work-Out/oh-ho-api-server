package kr.ohho.domain.member.converter;

import javax.persistence.AttributeConverter;
import kr.ohho.domain.member.Age;

public class AgeConverter implements AttributeConverter<Age, String> {

    @Override
    public String convertToDatabaseColumn(Age attribute) {
        return attribute.getCode();
    }

    @Override
    public Age convertToEntityAttribute(String dbData) {
        return Age.ofAge(dbData);
    }
}
