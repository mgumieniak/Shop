package shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.domens.RegistrationForm;
import shop.service.RegistryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistryService registryService;

    @GetMapping
    public String registerForm(Model model){
        RegistrationForm registrationForm = new RegistrationForm();
        model.addAttribute("newUser",registrationForm);
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("newUser") @Valid RegistrationForm form, BindingResult result) {

        if(result.hasErrors()){
            return "registration";
        }
        registryService.processRegistration(form);
        return "redirect:/login";
    }
}
