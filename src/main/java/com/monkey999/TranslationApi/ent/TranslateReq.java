package com.monkey999.TranslationApi.ent;

public class TranslateReq {

    /**
     * 翻訳テキスト
     */
    public String text;

    /**
     * 翻訳元
     */
    public String source;

    /**
     * 　翻訳先 TODO: Enumにしたい
     */
    public String target;

    /**
     * 使用する翻訳API
     * google or deepl
     * todo: スネークケースで受け取るには
     */
    public String translationClient;

}
