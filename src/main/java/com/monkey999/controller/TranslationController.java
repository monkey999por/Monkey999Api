package com.monkey999.controller;

import com.monkey999.ent.translation.TranslationReq;
import com.monkey999.ent.translation.TranslationRes;
import com.monkey999.service.translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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