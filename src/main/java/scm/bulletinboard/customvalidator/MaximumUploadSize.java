package scm.bulletinboard.customvalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import scm.bulletinboard.customvalidator.ConstraintValidator.MaximumUploadSizeValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaximumUploadSizeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaximumUploadSize {
    String message() default "File size must not exceed 3 MB";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}