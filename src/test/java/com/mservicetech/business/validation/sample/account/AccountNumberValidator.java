package com.mservicetech.business.validation.sample.account;

import com.mservicetech.business.validation.AnnotationBaseValidator;
import com.mservicetech.business.validation.BaseValidator;
import com.mservicetech.business.validation.ValidationCode;
import com.mservicetech.business.validation.ValidationResult;
import com.mservicetech.business.validation.annotations.Rule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Rule(id="0001", description = "account number validation", errorMessage = "Invalid account number.")
public class AccountNumberValidator implements AnnotationBaseValidator<String, Account> {
    private static final Logger logger = LoggerFactory.getLogger(AccountNumberValidator.class);
    public  final String ACCOUNT_FILTER = "ACCOUNT";

    @Override
    public boolean annotationSupport(String filter) {
        return ACCOUNT_FILTER.equalsIgnoreCase(filter) ;
    }

    @Override
    public Stream<ValidationResult> validate(Object context, Account payload) {
        List<ValidationResult> validationResults  = new ArrayList<>();
        ValidationResult validationResult = new ValidationResult(AccountNumberValidator.class);
        if (StringUtils.isBlank(payload.getTransit()) || StringUtils.isBlank(payload.getAccountId()))
        {
            validationResult.setError(true);
            validationResult.addValidationCodes(validationCodeByRule());
            logger.info("..." + getRuleDef().description());
        }
        validationResults.add(validationResult);
        return validationResults.stream();
    }
}
