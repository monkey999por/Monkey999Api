package com.monkey999.utils.external.api.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey999.constant.Const;
import com.monkey999.constant.TargetLang;
import com.monkey999.utils.tool.LangDetector;
import monkey999.tools.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TranslationClientOfDeepL implements TranslationClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    Logger logger = LoggerFactory.getLogger(TranslationClientOfDeepL.class);

    @Autowired
    LangDetector detector;

    private Boolean isLimits() {
//        logger.info(Setting.getAsString("deepl.url.check.limit"));
//        logger.info(Setting.getAsString("deepl.authorization"));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Setting.getAsString("deepl.url.check.limit")))
                .version(HttpClient.Version.HTTP_2)
                .header("Authorization", "DeepL-Auth-Key " + Setting.getAsString("deepl.authorization"))
                .header("User-Agent", "translation/1.2.3").build();

        try {
            logger.info(request.toString());
//            request.headers().map().forEach((k,v) -> {
//                logger.info(k);
//                v.forEach((w) -> {
//                    logger.info(w);
//                });
//            });
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info(response.body());
//            response.headers().map().forEach((k,v) -> {
//                logger.info(k);
//                v.forEach((w) -> {
//                    logger.info(w);
//                });
//            });

            if (response.statusCode() == 200) {
                HashMap<String, Integer> deeplUsage = (HashMap<String, Integer>) mapper.readValue(response.body(), new TypeReference<Map<String, Integer>>() {
                });

                logger.info("Number of characters used: {}", deeplUsage.get("character_count"));
                logger.info("number of characters available: {}", (deeplUsage.get("character_limit") - deeplUsage.get("character_count")));

                return deeplUsage.get("character_count") > deeplUsage.get("character_limit");
            } else {
                throw new Exception("http status code error: " + response.statusCode());
            }
        } catch (Exception e) {
            logger.error("ERROR Deepl is limit");
            logger.error(e.getMessage());
            // 例外時はその時点でアウト
            return true;
        }
    }

    @Override
    public String request(String text) throws Exception {
        return detector.isJapanese(text) ?
                request(text, TargetLang.JAPANESE, TargetLang.ENGLISH) :
                request(text, TargetLang.ENGLISH, TargetLang.JAPANESE);
    }

    @Override
    public String request(String text, TargetLang source, TargetLang target) throws Exception {
        if (isLimits()) {
            throw new Exception("DeepL翻訳は利用上限に達しています。詳細はhttps://www.deepl.com/ja/account/usageでご確認ください。");
        }
        String paramText = "";
        try {
            paramText = Objects.isNull(text) ? "" : Const.codec.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        Boolean isJapanese = source.languageCode.equals("ja");
        var paramSourceLang = isJapanese ? "JA" : "EN";
        var paramTargetLang = isJapanese ? "EN-US" : "JA";

        var requestBody = String.format("text=%s&source_lang=%s&target_lang=%s", paramText, paramSourceLang, paramTargetLang);
        logger.info("requestBody: {}", requestBody);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Setting.getAsString("deepl.uri")))
                .version(HttpClient.Version.HTTP_2)
                .header("Authorization", "DeepL-Auth-Key " + Setting.getAsString("deepl.authorization"))
                .header("User-Agent", "translation/1.2.3")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            // リクエストを送信
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonNode responseBody = mapper.readTree(response.body());
                String sourceLang = responseBody.get("translations").get(0).get("detected_source_language").asText();
                String translateResult = responseBody.get("translations").get(0).get("text").asText();
                logger.info("responseBody: {}", responseBody);
                return translateResult;
            } else {
                throw new Exception("ERROR" + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
