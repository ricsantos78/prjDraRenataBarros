package com.example.prjdrarenatabarros.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login/login");
    }

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("home/index");
    }
}
