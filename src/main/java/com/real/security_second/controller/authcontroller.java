package com.real.security_second.controller;

import com.real.security_second.service.userservice;
import jakarta.annotation.Nullable;
import org.hibernate.engine.internal.Nullability;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@Controller
public class authcontroller {

    private final userservice userservice;

    public authcontroller(userservice userservice) {
        this.userservice = userservice;
    }

    @GetMapping({"/","/login"})
    public String login(Model model , @RequestParam @Nullable String error , RedirectAttributes redirectAttributes) {

        if (error != null && (error.equals("true"))) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
        return "login-page";
    }

    @GetMapping("/dashboard")
    public String dashboard( Model model){
        return "dashboard";
    }

    @GetMapping({"/","/register"})
    public String register(Model model) {
        return "register-page";
    }

    @PostMapping("/register")
    public String dostring(@RequestParam("firstName") String firstname,
                           @RequestParam("lastName") String lastname,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           RedirectAttributes redirectAttributes
    ){
        try {
            userservice.registeruser(firstname,lastname,username,password);
            redirectAttributes.addFlashAttribute("success","Register successfully");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/register";
    }
}
