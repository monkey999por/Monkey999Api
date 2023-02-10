package com.monkey999.ent.interfaces.translation;

import com.monkey999.ent.interfaces.base.BaseReq;

public class TranslationReq extends BaseReq {

    /**
     * 翻訳テキスト
     */
    public String text;

    /**
     * 翻訳元
     */
    public String source;

    /**
     * 　翻訳先
     */
    public String target;

    /**
     * 使用する翻訳API
     * google or deepl
     */
    public String translation_client;

}
