package com.bingo.laboratory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author by tuyx
 *
 * @Created at : 2016/11/4 0004
 * @Description :
 * @VersionName :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface TestAnnotation {
    String value();
    String[] value2() default "top";
}
