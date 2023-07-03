package scm.bulletinboard.customvalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import scm.bulletinboard.customvalidator.ConstraintValidator.ImageFormatValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageFormatValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageFormat {
    String message() default "File must be an image";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}