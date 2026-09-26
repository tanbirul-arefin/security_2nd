package com.real.security_second.controller;

import com.real.security_second.service.userservice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class authcontroller {

    private final userservice userservice;

    public authcontroller(userservice userservice) {
        this.userservice = userservice;
    }

    @GetMapping({"/","/login"})
    public String login(Model model) {
        return "login-page";
    }

    @GetMapping({"/","/register"})
    public String register(Model model) {
        return "register-page";
    }

    @PostMapping("/register")
    public String dostring(@RequestParam String firstname,@RequestParam String lastname,
                           @RequestParam String username,@RequestParam String password ,
                           RedirectAttributes redirectAttributes
    ){
        try {
            userservice.registeruser(username,password,firstname,lastname);
            redirectAttributes.addFlashAttribute("success","Register successfully");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/register";
    }
}
