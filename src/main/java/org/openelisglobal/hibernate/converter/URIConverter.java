package org.openelisglobal.hibernate.converter;

import java.net.URI;
import java.net.URISyntaxException;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.openelisglobal.common.log.LogEvent;

@Converter
public class URIConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    @Override
    public URI convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return new URI(dbData);
        } catch (URISyntaxException e) {
            LogEvent.logError("could not convert string to uri", e);
            throw new RuntimeException(e);
        }
    }
}
