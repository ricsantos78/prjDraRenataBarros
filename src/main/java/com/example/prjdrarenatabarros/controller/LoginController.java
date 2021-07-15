package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Usuario;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView andView = new ModelAndView("login/login");
        return andView;
    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView andView = new ModelAndView("home/index");
        return andView;
    }
}
