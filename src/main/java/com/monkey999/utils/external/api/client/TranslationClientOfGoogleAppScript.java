package com.monkey999.utils.external.api.client;

import com.monkey999.constant.Const;
import com.monkey999.constant.TargetLang;
import com.monkey999.utils.tool.LangDetector;
import monkey999.tools.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * see https://script.google.com/home/projects/1sFWfV_MOJbmBeTLT_R9F4Qq0qPolgkRsVt8A_sCMI2C9DTccZBRdHFDt/edit
 */
@Component
public class TranslationClientOfGoogleAppScript implements TranslationClient {


    @Autowired
    LangDetector detector;


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
        return url + "?" + param;
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
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(requestUrl, String.class);
            return response;
        } catch (Exception e) {
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

        System.out.println(requestUrl);
        return translate(requestUrl);
    }

}
