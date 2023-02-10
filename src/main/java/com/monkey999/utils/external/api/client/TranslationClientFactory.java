package com.monkey999.utils.external.api.client;


import monkey999.tools.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslationClientFactory {

    @Autowired
    TranslationClientOfDeepL translationClientOfDeepL;

    @Autowired
    TranslationClientOfGoogleAppScript translationClientOfGoogleAppScript;

    public TranslationClient getInstance() {
        System.out.println(Setting.getAsString("translation_client"));
        return Setting.getAsString("translation_client").equals("deepl") ? translationClientOfDeepL :
                translationClientOfGoogleAppScript;
    }

}
