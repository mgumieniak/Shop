package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.data.UserRepository;
import shop.domens.RegistrationForm;

@Service
public class RegistryServiceImpl implements RegistryService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistryServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void processRegistration(RegistrationForm registrationForm) {
        userRepository.save(registrationForm.toUser(passwordEncoder));
    }
}
