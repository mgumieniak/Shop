package shop.domens;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.validator.EqualPasswords;

import javax.validation.constraints.NotNull;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String confirmPassword;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password));
    }
}
