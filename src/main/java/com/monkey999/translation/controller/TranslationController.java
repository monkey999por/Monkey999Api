package com.monkey999.translation.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.monkey999.translation.ent.TranslationReq;
import com.monkey999.translation.ent.TranslationRes;
import com.monkey999.translation.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TranslationController {

    @Autowired
    TranslationService service;

    @PostMapping(value = "/translate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TranslationRes translate(@RequestBody TranslationReq request) {
        return service.translate(request);
    }

}