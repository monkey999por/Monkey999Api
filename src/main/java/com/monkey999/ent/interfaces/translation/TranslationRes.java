package com.monkey999.ent.interfaces.translation;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey999.constant.TargetLang;
import com.monkey999.ent.interfaces.base.BaseRes;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TranslationRes extends BaseRes {
    private String text;

    @JsonProperty("language_code")
    private TargetLang languageCode;
}
