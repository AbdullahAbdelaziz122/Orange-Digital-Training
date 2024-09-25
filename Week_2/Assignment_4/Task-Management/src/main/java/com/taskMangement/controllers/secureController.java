package com.taskMangement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class secureController {


    @GetMapping("/secure")
    public String secure(){
        return "this is secure";
    }
}
