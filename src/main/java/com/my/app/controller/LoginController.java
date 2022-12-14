package com.my.app.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getViewForEveryone() {
        return "menu_info";
    }

    @GetMapping("/login")
    public ModelAndView show_authorization() {
        return new ModelAndView("login");
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView authorization() {
        return null;
    }
}
