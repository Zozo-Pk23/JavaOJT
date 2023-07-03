package scm.bulletinboard.customvalidator.ConstraintValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import scm.bulletinboard.customvalidator.ImageFormat;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ImageFormatValidator implements ConstraintValidator<ImageFormat, MultipartFile> {

    private static final List<String> ALLOWED_IMAGE_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");

    @Override
    public void initialize(ImageFormat constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file == null || file.isEmpty() || ALLOWED_IMAGE_CONTENT_TYPES.contains(file.getContentType());
    }
}