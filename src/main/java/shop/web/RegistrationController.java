package shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.domens.RegistrationForm;
import shop.service.RegistryService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistryService registryService;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        registryService.processRegistration(form);
        return "redirect:/login";
    }
}
