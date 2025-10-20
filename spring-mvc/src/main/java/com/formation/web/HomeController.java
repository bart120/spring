package com.formation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "Hello Spring MVC (Jetty 11) !");
        return "index";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "Bob") String name, Model model) {
        model.addAttribute("msg", "Hello " + name + "!");
        return "hello";
    }
}