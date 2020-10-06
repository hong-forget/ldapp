package com.ld.ldapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class JspController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "张佳");
        System.out.println(model);
        return "index";
    }

}

