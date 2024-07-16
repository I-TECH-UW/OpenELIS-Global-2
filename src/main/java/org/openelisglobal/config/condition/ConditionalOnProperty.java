package org.openelisglobal.config.condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Conditional;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Conditional(PropertyCondition.class)
public @interface ConditionalOnProperty {
    /** The name of the property. If not found, it will evaluate to false. */
    String[] properties();

    String[] havingValues() default {};

    /** If true, the property value should not be empty or null. */
    boolean[] nonEmpty() default {};

    /** if the property is missing what value should this default to */
    boolean matchIfMissing() default false;
}
