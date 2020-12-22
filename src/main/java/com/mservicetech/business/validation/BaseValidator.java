package com.mservicetech.business.validation;

import java.util.stream.Stream;

public interface BaseValidator <T, PayloadType> {

    boolean support (T filter);

    Stream<ValidationResult> validate(Object context, PayloadType payload);
}
