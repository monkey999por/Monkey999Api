package com.monkey999.ent.interfaces.translation;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey999.constant.TargetLang;
import com.monkey999.ent.interfaces.base.BaseRes;

public class TranslationRes extends BaseRes {
    private String text;

    @JsonProperty("language_code")
    private TargetLang languageCode;

    public TargetLang getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(TargetLang languageCode) {
        this.languageCode = languageCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
