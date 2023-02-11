package com.monkey999.ent.interfaces.translation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey999.ent.interfaces.base.BaseReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TranslationReq extends BaseReq {
    private String text;
    private String source;
    private String target;
    @JsonProperty("translation_client")
    private String translationClient;
}
