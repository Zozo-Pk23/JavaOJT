package scm.bulletinboard.customvalidator.ConstraintValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import scm.bulletinboard.customvalidator.MaximumUploadSize;

import org.springframework.web.multipart.MultipartFile;

public class MaximumUploadSizeValidator implements ConstraintValidator<MaximumUploadSize, MultipartFile> {

    private static final long MAX_FILE_SIZE = 3 * 1024 * 1024; // 3 MB

    @Override
    public void initialize(MaximumUploadSize constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file == null || file.isEmpty() || file.getSize() <= MAX_FILE_SIZE;
    }
}