package com.monkey999.ent.interfaces.translation;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey999.constant.TargetLang;
import com.monkey999.ent.interfaces.base.BaseRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TranslationRes extends BaseRes {
    private String text;

    private TargetLang source = TargetLang.ANY;

    private TargetLang target = TargetLang.ANY;
}
