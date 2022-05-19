package com.example.jxls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebAppController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/hello/eng")
    public String helloEng() {
        return "greet/helloEng";
    }

    @GetMapping("/hello/rus")
    public String helloRus() {
        return "greet/helloRus";
    }

    @GetMapping("/hello/esp")
    public String helloEsp() {
        return "greet/helloEsp";
    }

    @GetMapping("/styles1")
    public String testStyles1() {
        return "tests/styles1";
    }

    @GetMapping("/styles2")
    public String testStyles2() {
        return "tests/styles2";
    }

    @GetMapping("/styles3")
    public String testStyles3() {
        return "tests/styles3";
    }

}
