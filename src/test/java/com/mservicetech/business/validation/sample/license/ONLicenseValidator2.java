package com.mservicetech.business.validation.sample.license;

import com.mservicetech.business.validation.BaseValidator;
import com.mservicetech.business.validation.ValidationCode;
import com.mservicetech.business.validation.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ONLicenseValidator2 implements BaseValidator<String, DrivingLicense> {


    public  final String LICENSE_FILTER = "ON";

    @Override
    public boolean support(String filter) {
        return LICENSE_FILTER.equalsIgnoreCase(filter);
    }

    @Override
    public int priority() {
        return 99;
    }

    @Override
    public Stream<ValidationResult> validate(Object context, DrivingLicense payload) {
        List<ValidationResult> validationResults  = new ArrayList<>();
        ValidationResult validationResult = new ValidationResult(ONLicenseValidator2.class);
        if (payload.getDriverLastName()!=null && !payload.getDriverLastName().substring(0,1).equalsIgnoreCase(payload.getDrivingLicenseNumber().substring(0,1))) {
            validationResult.setError(true);
            validationResult.addValidationCodes(new ValidationCode("1102", "Invalid ON driving license number by driver last name"));
        }
        validationResults.add(validationResult);
        return validationResults.stream();
    }
}
