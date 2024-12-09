package com.darkoum.darkoum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password) {
        // Handle login logic here (e.g., check credentials)
        return "redirect:/home"; // Redirect to home page after successful login
    }

    @GetMapping
    public String loginPage() {
        return "components/login"; // Return the login page
    }
}
