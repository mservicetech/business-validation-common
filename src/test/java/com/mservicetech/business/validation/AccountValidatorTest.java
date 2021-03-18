package com.mservicetech.business.validation;

import com.mservicetech.business.validation.exception.BusinessValidationException;
import com.mservicetech.business.validation.sample.account.Account;
import com.mservicetech.business.validation.sample.account.AccountBalanceValidator;
import com.mservicetech.business.validation.sample.account.AccountNumberValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AccountValidatorTest {

    List<BaseValidator> validators;
    private Account account;
    public  final String ACCOUNT_FILTER = "ACCOUNT";

    @Before
    public  void setUp() {
        account = new Account();
        account.setAccountId("111-5698");
        account.setTransit("001");
        account.setAccountName("New Saving");
        BaseValidator accountNumberValidator = new AccountNumberValidator();

        validators = new ArrayList<>();
        validators.add(accountNumberValidator);
    }

    @Test
    public void testAccountNumberValid() {
        List<ValidationCode> codes = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(ACCOUNT_FILTER)).flatMap(v->v.validate(null,account));
        validationResultStream.filter(v->v.isError()).forEach(v->codes.addAll(v.getValidationCodes()));
        Assert.assertEquals( codes.size(), 0);
    }

    @Test (expected = BusinessValidationException.class)
    public void testAccountNumberInValid() {
        account.setTransit(null);
        List<ValidationCode> codes = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(ACCOUNT_FILTER)).flatMap(v->v.validate(null,account));
        validationResultStream.filter(v->v.isError()).forEach(v->codes.addAll(v.getValidationCodes()));
        Assert.assertTrue( codes.size()>0);
        if (codes.size()>0) {
            String errMsg = "Business validation error:" + ValidationResultUtil.getValidationMessage(codes);
            throw new BusinessValidationException("Error", errMsg);
        }
    }

    @Test
    public void testAccountBalanceValid() {
        account.setBalance(100.00);
        BaseValidator accountBalanceValidator = new AccountBalanceValidator();
        validators.add(accountBalanceValidator);
        List<ValidationCode> codes = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(ACCOUNT_FILTER)).flatMap(v->v.validate(null,account));
        validationResultStream.filter(v->v.isError()).forEach(v->codes.addAll(v.getValidationCodes()));
        Assert.assertEquals( codes.size(), 0);
    }

    @Test (expected = BusinessValidationException.class)
    public void testAccountBalanceInValid() {
        account.setBalance(-100.00);
        BaseValidator accountBalanceValidator = new AccountBalanceValidator();
        validators.add(accountBalanceValidator);
        List<ValidationCode> codes = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(ACCOUNT_FILTER)).flatMap(v->v.validate(null,account));
        validationResultStream.filter(v->v.isError()).forEach(v->codes.addAll(v.getValidationCodes()));
        Assert.assertTrue( codes.size()>0);
        if (codes.size()>0) {
            String errMsg = "Business validation error:" + ValidationResultUtil.getValidationMessage(codes);
            throw new BusinessValidationException("Error", errMsg);
        }
    }
}
