package com.real.security_second.controller;

import com.real.security_second.service.userservice;
import jakarta.annotation.Nullable;
import org.hibernate.engine.internal.Nullability;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@Controller
public class authcontroller {

    private final userservice userservice;

    public authcontroller(userservice userservice) {
        this.userservice = userservice;
    }

    @GetMapping({"/","/login"})
    public String login(@RequestParam @Nullable String error , RedirectAttributes redirectAttributes) {

        if (error != null && (error.equals("true"))) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
        return "login-page";
    }


    @GetMapping({"/","/register"})
    public String register() {
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

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        if (authorities.get(0).getAuthority().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboardPage() {
        return "admin-dashboard";
    }

    @GetMapping("/user/dashboard")
    public String usreDashboardPage() {
        return "user-dashboard";
    }

}
