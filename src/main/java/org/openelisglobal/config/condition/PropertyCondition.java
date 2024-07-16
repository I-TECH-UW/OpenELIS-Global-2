package org.openelisglobal.config.condition;

import java.util.Map;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class PropertyCondition implements ConfigurationCondition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnProperty.class.getName());
        if (attributes == null)
            return false;
        String[] properties = (String[]) attributes.get("properties");
        String[] havingValues = (String[]) attributes.get("havingValues");
        boolean[] nonEmpty = (boolean[]) attributes.get("nonEmpty");
        boolean matchIfMissing = (boolean) attributes.get("matchIfMissing");

        if (properties == null || properties.length == 0) {
            return matchIfMissing;
        }

        for (int i = 0; i < properties.length; i++) {
            String property = properties[i];
            String propertyValue = context.getEnvironment().getProperty(property);

            boolean valueCheck = (havingValues.length > i && propertyValue != null)
                    ? propertyValue.equals(havingValues[i])
                    : true;

            boolean nonEmptyCheck = (nonEmpty.length > i) ? nonEmpty[i] : false;

            if (propertyValue == null) {
                if (!matchIfMissing) {
                    return false;
                }
            } else if (nonEmptyCheck && propertyValue.isEmpty()) {
                return false;
            } else if (!valueCheck) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }
}
