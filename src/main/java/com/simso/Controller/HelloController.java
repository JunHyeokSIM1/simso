package com.simso.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")

public class HelloController {

    @RequestMapping(value = "/{firstName}/{lastName}", method = RequestMethod.GET)
    public String hello(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){

        return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
    }

    @GetMapping("hello-mvc")
    public String helloMvc(Model model){
        model.addAttribute("name", "hello-mvc");
        return "hello-mvc";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public String helloApi(@RequestParam("name") String name){
        return "hello: " + name;
    }
}
