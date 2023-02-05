package com.monkey999.TranslationApi.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.monkey999.TranslationApi.ent.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public String test() {
        System.out.println("request to /");
        return "Rest API test page";
    }


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("request to /greeting");
        return new Greeting(counter.incrementAndGet(), String.format(template, "takasi"));
    }

    @PostMapping("/post")
    public Greeting greetingPost(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, "post_" + name));
    }
}