package scm.bulletinboard.customvalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import scm.bulletinboard.customvalidator.ConstraintValidator.NotBlankFileValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotBlankFileValidator.class) 
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankFile {
    String message() default "File must not be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
