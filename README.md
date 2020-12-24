# business-validation-common

Define base interface and common logic for API business validation

[![Build Status](https://travis-ci.com/mservicetech/business-validation-common.svg?branch=main)](https://travis-ci.com/github/mservicetech/business-validation-common) 


## Summary

In microservice API, normally it has validation in two levels:

- Openapi schema validation

  Field level validation for request/response in the openapi specification.
  
  For example: required fields, data type, data format...
  
- Business validation
  
  Certain business rule validation for which cannot be defined the detail in the openapi specification
  
  For example, validate account number for different account type; validate driving license number issued from different country/place

For the openapi schema validation, schema validation can be applied directly. Some microservice framwork embedded it with the framework; 
for example, sprint-boot integrate with swagger codegen and bean validation, light-4j integration with request/response handler chain cross-cutting concerns.

And in the application,  schema validation can be applied  by applying library directly:

https://github.com/mservicetech/openapi-schema-validation 


For business validation, some monolithic application use rule engineer for it. But rule engineer is too heavy for microservice API.
And developers more like to build the business validation in pure java code.

Ideally, we would like to build the complex business validation to small and light validation validators as validation chain.
The validator chain can be plugin to the service class to controller/handler which will not impact the business logic implmentation in the service/controller/handler classes.
  

## Implementation detail:

For validator implementation, please refer the example in the /sample package



To invoke the validator class list stream

- Initial validators (get the list validators which implements BaseValidator interface )

For example, on Sprint-boot:

```
  @Autowired
  List<BaseValidator> validators;

```

- Invoke the validators

```
        List<ValidationCode> codes = new ArrayList<>();
        Stream<ValidationResult> validationResultStream = validators.stream().filter(validator->validator.support(FILTER_NAME)).flatMap(v->v.validate(null,drivingLicense));
        validationResultStream.filter(v->v.isError()).forEach(v->codes.addAll(v.getValidationCodes()));
        if (codes.size()>0) {
            //TODO throw exception or return error codes
        }

```


 