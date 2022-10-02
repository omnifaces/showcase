package org.omnifaces.showcase.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = { ProductValidator.class })
@Documented
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public @interface ValidProduct {
	String message() default "Invalid product";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
