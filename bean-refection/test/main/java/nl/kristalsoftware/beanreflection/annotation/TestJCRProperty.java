package nl.kristalsoftware.beanreflection.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by sjoerdadema on 30-06-15.
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface TestJCRProperty {
    String name() default "";
}
