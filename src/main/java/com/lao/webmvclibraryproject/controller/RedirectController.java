package com.lao.webmvclibraryproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class RedirectController {

    @GetMapping()
    public String index() {
        return "redirect:/books";
    }

}
