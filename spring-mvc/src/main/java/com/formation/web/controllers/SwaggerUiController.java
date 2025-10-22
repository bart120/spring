package com.formation.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerUiController {

    // Rend l’URL standard accessible : /swagger-ui/index.html
    @GetMapping("/swagger-ui/index.html")
    public String redirectToUi() {
        // Le webjar sert l’index à /webjars/swagger-ui/index.html
        // On lui passe la config springdoc standard :
        return "redirect:/webjars/swagger-ui/index.html?configUrl=http://localhost:8080/v3/api-docs";
    }

    // (confort) /swagger-ui -> même chose
    @GetMapping("/swagger-ui")
    public String redirectRoot() {
        return "redirect:/swagger-ui/index.html";
    }
}