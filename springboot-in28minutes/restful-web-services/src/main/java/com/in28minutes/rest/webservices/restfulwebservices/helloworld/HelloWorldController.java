package com.in28minutes.rest.webservices.restfulwebservices.helloworld;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

// @Controller
@RestController
public class HelloWorldController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // @ResponseBody
    // @RequestMapping(path = "/hello-world", method = RequestMethod.GET)
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable("name") String name) {
        logger.info("name - {}", name);
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());

        //return "Hello World V2";

        //1:
        //2:
        //		- Example: `en` - English (Good Morning)
        //		- Example: `nl` - Dutch (Goedemorgen)
        //		- Example: `fr` - French (Bonjour)
        //		- Example: `de` - Deutsch (Guten Morgen)

    }
}
