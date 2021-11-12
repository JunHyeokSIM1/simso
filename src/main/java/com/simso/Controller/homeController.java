package com.simso.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String home(){
        return "/home";
    }

    @GetMapping("/api")
    public String api(){
        return "/index";
    }

}
