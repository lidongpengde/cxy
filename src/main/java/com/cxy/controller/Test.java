package com.cxy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lidp on 2017/3/18.
 */
@Controller
public class Test {
    @Autowired
   // BookService service;
    @RequestMapping(value = "/admin")
    @ResponseBody
    public String testSpring(){
        //Book book=service.getBookById(1000);

        return "My testSpring";
    }
}
