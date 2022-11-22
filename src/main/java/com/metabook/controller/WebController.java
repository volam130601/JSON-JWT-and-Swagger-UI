package com.metabook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class WebController {
    @GetMapping
    public String home() {
        return "Hello ADMIN";
    }
}
