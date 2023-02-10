package com.monkey999.utils.external.api.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslationClientFactory {

    @Autowired
    TranslationClientOfDeepL translationClientOfDeepL;

    @Autowired
    TranslationClientOfGoogleAppScript translationClientOfGoogleAppScript;

    public TranslationClient getInstance(String client) {
        return "deepl".equals(client) ? translationClientOfDeepL :
                translationClientOfGoogleAppScript;
    }

}
