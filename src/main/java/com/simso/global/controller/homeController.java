package com.simso.global.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class homeController {


    @GetMapping("/")
    public String home(Model model) {
            model.addAttribute("userName");
        return "/home";
    }
//    @GetMapping("/")
//    public String home(Model model, @LoginUser SessionUser user) {
//
//        if (user != null) {
//            model.addAttribute("userName", user.getName());
//        }
//        return "/home";
//    }



}
