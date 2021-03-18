package com.mservicetech.business.validation;

import com.mservicetech.business.validation.annotations.Rule;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Define annotation base business validation interface default methods. .
 *
 * @author Gavin Chen
 */
public interface AnnotationBaseValidator<T, PayloadType> extends BaseValidator<T, PayloadType> {

    default boolean support(T filter) {
        return  isValidTimeRange() &&  annotationSupport(filter);
    }

    boolean annotationSupport (T filter);

    /**
     * default method to get rule  annotation rule definition info.
     *
     */
    default Rule getRuleDef() {
        Rule rule = getClass().getAnnotation(Rule.class);
        return rule;
    }

    /**
     * default method to generate validation result code object from rule annotation.
     *
     */
    default ValidationCode validationCodeByRule() {
        Rule rule = getRuleDef();
        if (rule!=null) {
            return new ValidationCode(rule.id(), rule.errorMessage());
        }
        return null;
    }

    /**
     * default method to check id the rule in Valid time range to be executed base on rule definition.
     *
     */
    default boolean isValidTimeRange() {
        Rule rule = getRuleDef();
        if (rule!=null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = new Date();
            try {
                Date startDate = dateFormat.parse(rule.effectiveDate());
                Date endDate = dateFormat.parse(rule.endDate());
                return todayDate.after(startDate) && endDate.after(todayDate);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                return true;
            }

        }
        return true;
    }

}
