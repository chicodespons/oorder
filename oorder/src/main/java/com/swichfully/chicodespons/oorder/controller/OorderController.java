package com.swichfully.chicodespons.oorder.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.awt.*;

@RestController
@RequestMapping(path = "/")
public class OorderController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String HelloWorld() {
        return "Hello World";
    }
}
