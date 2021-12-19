package com.lab.bss.fourlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SPAController {

    /**
     * Возвращает html страницу
     */
    @GetMapping("/")
    public String getPage() {
        return "index";
    }

}
