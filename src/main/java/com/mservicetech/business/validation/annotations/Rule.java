package com.mservicetech.business.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Rule {

    String id();

    int version() default 1;

    String description();

    String errorMessage();

    String effectiveDate() default  "2000-01-01";

    String  endDate() default "2100-12-31";
}
