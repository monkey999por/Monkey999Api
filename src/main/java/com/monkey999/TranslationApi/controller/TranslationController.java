package com.monkey999.TranslationApi.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.monkey999.TranslationApi.ent.Greeting;
import com.monkey999.TranslationApi.ent.ServerError;
import com.monkey999.TranslationApi.ent.TestRequest;
import com.monkey999.TranslationApi.ent.TranslateReq;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TranslationController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public String test() {
        System.out.println("request to /");
    return "Rest API test dpaffged raspi deploy deone!!";
    }


    // これ正解
    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String test(@RequestBody TestRequest request) {
        System.out.println(request.toString());

        return request.toString();
    }

    @PostMapping("/test555")
    public Greeting testb(@RequestParam TranslateReq req) {

        try {

            System.out.println("request to /greeting");
            System.out.println(req.text);
            System.out.println(req.source);
            return new Greeting(counter.incrementAndGet(), String.format(template, "takasddi"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new Greeting(1L, "error");
        }
    }

//    @GetMapping("/errord")
//    public ServerError error() {
//        return new ServerError("500", "E01");
//    }

    @PostMapping("/post")
    public Greeting greetingPost(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, "post_" + name));
    }
}