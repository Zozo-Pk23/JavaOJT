package scm.bulletinboard.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordForm {
    @NotBlank(message = "Current Password can not be blank.")
    private String oldPassword;
    @NotBlank(message = "New Password can not be blank.")
    private String newPassword;
    @NotBlank(message = "New Confirm Password can not be blank.")
    private String newConfirmPassword;
}
