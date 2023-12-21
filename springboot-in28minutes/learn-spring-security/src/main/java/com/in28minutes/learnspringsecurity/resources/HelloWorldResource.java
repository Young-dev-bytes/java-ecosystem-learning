package com.in28minutes.learnspringsecurity.resources;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {


    @GetMapping("/hello-world")
    public String helloWorld(HttpServletRequest request) {

        HttpSession session = request.getSession();
        System.out.println(session.getAttributeNames());
        return "hello-world";
    }
}
