package com.monkey999.utils.external.api.client;

import com.monkey999.constant.TargetLang;
import com.monkey999.utils.tool.LangDetector;
import monkey999.tools.Setting;
import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * see https://script.google.com/home/projects/1sFWfV_MOJbmBeTLT_R9F4Qq0qPolgkRsVt8A_sCMI2C9DTccZBRdHFDt/edit
 */
@Component
public class TranslationClientOfGoogleAppScript implements TranslationClient {

    Logger logger = LoggerFactory.getLogger(TranslationClientOfGoogleAppScript.class);

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
    public static String createRequestUrl(String text, TargetLang source, TargetLang target) throws Exception {

        // Spring bootがいい感じにエンコードもしてくれるからこっちでやる必要ない
//        text = Objects.isNull(text) ? "" : Const.codec.encode(text, "UTF-8");
        text = Objects.isNull(text) ? "" : text;


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
    public static String translate(String requestUrl) throws DecoderException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(requestUrl, String.class);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String request(String text) throws Exception {
        String requestUrl = detector.isJapanese(text)
                ? createRequestUrl(text, TargetLang.JAPANESE, TargetLang.ENGLISH)
                : createRequestUrl(text, TargetLang.ENGLISH, TargetLang.JAPANESE);

        logger.info(requestUrl);
        return translate(requestUrl);
    }

    @Override
    public String request(String text, TargetLang source, TargetLang target) throws Exception {
        return translate(createRequestUrl(text, source, target));
    }


}
