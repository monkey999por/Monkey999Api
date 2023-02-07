package com.monkey999.translation.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.monkey999.translation.ent.TranslationReq;
import com.monkey999.translation.ent.TranslationRes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TranslationController {
    @PostMapping(value = "/translate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TranslationRes translate(@RequestBody TranslationReq request) {
        return new TranslationRes();
    }

//    @PostMapping("/test555")
//    public Greeting testb(@RequestParam TranslateReq req) {
//
//        try {
//
//            System.out.println("request to /greeting");
//            System.out.println(req.text);
//            System.out.println(req.source);
//            return new Greeting(counter.incrementAndGet(), String.format(template, "takasddi"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return new Greeting(1L, "error");
//        }
//    }

//    @GetMapping("/errord")
//    public ServerError error() {
//        return new ServerError("500", "E01");
//    }

//    @PostMapping("/post")
//    public Greeting greetingPost(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, "post_" + name));
//    }
}