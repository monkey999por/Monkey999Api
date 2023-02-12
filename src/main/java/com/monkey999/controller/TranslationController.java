package com.monkey999.controller;

import com.monkey999.ent.interfaces.translation.TranslationReq;
import com.monkey999.ent.interfaces.translation.TranslationRes;
import com.monkey999.service.translation.TranslationService;
import com.monkey999.validate.TranslationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TranslationController {

    Logger logger = LoggerFactory.getLogger(TranslationController.class);

    @Autowired
    TranslationValidator validator;
    @Autowired
    TranslationService service;

    @PostMapping(value = "/translate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TranslationRes> translate(@RequestBody TranslationReq request) throws Exception {

        final var validateResult = validator.validate(request);
        if (validateResult.noProblem) {
            logger.info(request.toString());
            var result = service.translate(request);
            logger.info(result.toString());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(new TranslationRes() {{

            }});

        }

    }

}