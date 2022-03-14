package com.simso.Controller;

import com.simso.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class homeController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model){

        SessionUser user = (SessionUser)  httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName" , user.getName());
        }
        return "/home";
    }

    @GetMapping("/api")
    public String api(){
        return "/index";
    }

}
