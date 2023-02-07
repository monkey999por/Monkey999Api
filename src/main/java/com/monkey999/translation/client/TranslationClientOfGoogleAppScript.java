package com.monkey999.translation.client;

import com.monkey999.utils.constant.Const;
import com.monkey999.utils.constant.TargetLang;
import com.monkey999.utils.tool.LangDetector;
import com.monkey999.utils.tool.LangDetectorFactory;
import org.apache.commons.codec.net.URLCodec;


import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * see https://script.google.com/home/projects/1sFWfV_MOJbmBeTLT_R9F4Qq0qPolgkRsVt8A_sCMI2C9DTccZBRdHFDt/edit
 */
public class TranslationClientOfGoogleAppScript implements TranslationClient {


    private final LangDetector detector = LangDetectorFactory.newInstance();

    public TranslationClientOfGoogleAppScript() {

    }

    /**
     * create request URL as google apps script as "google_translate_api".
     *
     * @param text   translate text
     * @param source before language
     * @param target after language
     * @return request URL as google apps script as "google_translate_api"
     */
    public static String createRequestUrl(String text, TargetLang source, TargetLang target) {
        try {
            text = Objects.isNull(text) ? "" : Const.codec.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        String url = Setting.getAsString("translate_request_url");
        String param = Setting.getAsString("translate_request_param")
                .replace("{text}", text)
                .replace("{source}", source.languageCode)
                .replace("{target}", target.languageCode);
        return new StringBuilder().append(url).append("?").append(param).toString();
    }

    /**
     * translate by URL.
     *
     * @param requestUrl created by {@link TranslationClientOfGoogleAppScript#createRequestUrl(String, TargetLang, TargetLang)}.
     * @return translate result.
     */
    public static String translate(String requestUrl) {
        try {
            // TODO: javaのHTTP clientを使用する
//            return Cmd.execute(false, new String[]{"curl", "-L", "-s", requestUrl});
        }catch (Exception e) {
            // とりあえずつぶしとけ
            return "API ERROR";
        }
    }

    @Override
    public String request(String text) {
        text = Objects.isNull(text) ? "" : text;
        String requestUrl = detector.isJapanese(text)
                ? createRequestUrl(text, TargetLang.JAPANESE, TargetLang.ENGLISH)
                : createRequestUrl(text, TargetLang.ENGLISH, TargetLang.JAPANESE);

        return translate(requestUrl);
    }

}
