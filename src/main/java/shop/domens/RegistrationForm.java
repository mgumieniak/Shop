package shop.domens;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.validator.EqualPasswords;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualPasswords
public class RegistrationForm {

    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    @Size(min=2, message = "Password must contain minimum 2 elements" )
    private String password;
    @NotNull(message = "ConfirmPassword is required")
    private String confirmPassword;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password));
    }
}
