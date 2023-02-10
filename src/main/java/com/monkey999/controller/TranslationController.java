package com.monkey999.controller;

import com.monkey999.ent.interfaces.ErrorRes;
import com.monkey999.ent.interfaces.base.BaseRes;
import com.monkey999.ent.interfaces.translation.TranslationReq;
import com.monkey999.service.translation.TranslationService;
import com.monkey999.validate.TranslationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {

    @Autowired
    TranslationValidator validator;
    @Autowired
    TranslationService service;

    @PostMapping(value = "/translate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseRes> translate(@RequestBody TranslationReq request) {

        final var validateResult = validator.validate(request);
        if (validateResult.noProblem) {
            var result = service.translate(request);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(new ErrorRes(){{
                setMessage("Validation Error.");
            }});
        }

    }

}