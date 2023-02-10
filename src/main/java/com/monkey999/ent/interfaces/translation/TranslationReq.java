package com.monkey999.ent.interfaces.translation;

import com.monkey999.ent.interfaces.base.BaseReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@Setter
@ToString(callSuper = true)
public class TranslationReq extends BaseReq {
    private String text;
    private String source;
    private String target;
    private String translationClient;

    @ConstructorProperties({"text", "source", "target", "translation_client"})
    public TranslationReq(String text, String source, String target, String translationClient) {
        this.text = text;
        this.source = source;
        this.target = target;
        this.translationClient = translationClient;
    }
}
