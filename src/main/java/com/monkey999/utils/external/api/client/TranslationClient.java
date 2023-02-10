package com.monkey999.utils.external.api.client;

import com.monkey999.constant.TargetLang;
import org.springframework.stereotype.Component;

@Component
public interface TranslationClient {
    /**
     * translate by text as...
     * 1. text is english -> translate to japanese
     * 2. text is japanese -> translate to english
     *
     * @param text translate text.
     * @return translate result. nomal text
     */
    String request(String text) throws Exception;

    /**
     * @param text   翻訳するテキスト
     * @param source 翻訳元言語コード
     * @param target 翻訳先言語コード
     * @return
     * @throws Exception
     */
    String request(String text, TargetLang source, TargetLang target) throws Exception;


}
