package shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.service.CustomerService;

@Controller
@RequestMapping("webstore/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String process(Model model){
        model.addAttribute("clients",customerService.getAllCustomers());
        return "listClients";
    }
}
