package org.openelisglobal.hibernate.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class StringToIntegerConverter implements AttributeConverter<String, Integer> {

  @Override
  public Integer convertToDatabaseColumn(String attribute) {
    return Integer.valueOf(attribute);
  }

  @Override
  public String convertToEntityAttribute(Integer dbData) {
    return dbData.toString();
  }
}
