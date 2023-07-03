package scm.bulletinboard.customvalidator.ConstraintValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import scm.bulletinboard.customvalidator.NotBlankFile;

import org.springframework.web.multipart.MultipartFile;

public class NotBlankFileValidator implements ConstraintValidator<NotBlankFile, MultipartFile> {

    @Override
    public void initialize(NotBlankFile constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file != null && !file.isEmpty();
    }
}


