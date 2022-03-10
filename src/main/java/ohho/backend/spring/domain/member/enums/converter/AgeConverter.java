package ohho.backend.spring.domain.member.enums.converter;

import javax.persistence.AttributeConverter;
import ohho.backend.spring.domain.member.enums.Age;

public class AgeConverter implements AttributeConverter<Age, String> {

    @Override
    public String convertToDatabaseColumn(Age attribute) {
        return attribute.getNumber();
    }

    @Override
    public Age convertToEntityAttribute(String dbData) {
        return Age.ofAge(dbData);
    }
}
