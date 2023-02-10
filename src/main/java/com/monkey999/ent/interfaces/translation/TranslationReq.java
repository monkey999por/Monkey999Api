package com.monkey999.ent.interfaces.translation;

import com.monkey999.constant.Const;
import com.monkey999.ent.interfaces.base.BaseReq;

import java.beans.ConstructorProperties;

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

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "text: " + text + Const.br +
                "source: " + source + Const.br +
                "target: " + target + Const.br +
                "translationClient: " + translationClient + Const.br +
                "apiKey: " + super.getCertification().getApiKey() + Const.br;
    }
}
