package net.romatic.jade.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author huiren
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MappingClass {
    Class value();
}
