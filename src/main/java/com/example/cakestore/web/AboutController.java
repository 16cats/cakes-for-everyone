package com.example.cakestore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String aboutPage() {
        return "about"; // This corresponds to the name of your Thymeleaf template (about.html)
    }
}