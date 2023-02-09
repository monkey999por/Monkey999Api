package com.monkey999.translation.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey999.translation.service.TranslationService;
import com.monkey999.utils.constant.Const;
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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TranslationClientOfDeepL implements TranslationClient {

    private static final boolean available = true;
    private static final ObjectMapper mapper = new ObjectMapper();

    Logger logger = LoggerFactory.getLogger(TranslationClientOfDeepL.class);

    @Autowired
    LangDetector detector;

    public TranslationClientOfDeepL() {

//        // 利用上限がきている場合は利用不可
//        if (isLimits()) {
//            synchronized (TranslationClientOfDeepL.class) {
//                TranslationClientOfDeepL.available = false;
//            }
//        }

    }

    private Boolean isLimits() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Setting.getAsString("deepl.url.check.limit")))
                .version(HttpClient.Version.HTTP_2)
                .header("Authorization", "DeepL-Auth-Key " + Setting.getAsString("deepl.authorization"))
                .header("User-Agent", "translation/1.2.3").build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                HashMap<String, Integer> deeplUsage = (HashMap<String, Integer>) mapper.readValue(response.body(), new TypeReference<Map<String, Integer>>() {
                });

                System.out.println("現在の利用文字数: " + deeplUsage.get("character_count"));
                System.out.println("残りの翻訳可能文字数: " + (deeplUsage.get("character_limit") - deeplUsage.get("character_count")));

                return deeplUsage.get("character_count") > deeplUsage.get("character_limit");
            } else {
                throw new Exception("http status code error: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 例外時はその時点でアウト
            return true;
        }
    }

    @Override
    public String request(String text) {
        logger.info(text);
        logger.info(Setting.getAsString("deepl.authorization"));

        // 文字数上限 1000文字まで
        if (text == null || text.getBytes(StandardCharsets.UTF_8).length > 3000) {
            return "翻訳できる文字数の上限を超えています。";
        }

        if (!TranslationClientOfDeepL.available) {
            return "DeepL翻訳は利用上限に達しています。詳細はhttps://www.deepl.com/ja/account/usageでご確認ください。";
        }

        String paramText = "";
        try {
            paramText = Objects.isNull(text) ? "" : Const.codec.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        Boolean isJapanese = detector.isJapanese(text);
        var paramSourceLang = isJapanese ? "JA" : "EN";
        var paramTargetLang = isJapanese ? "EN-US" : "JA";

        var requestBody = String.format("text=%s&source_lang=%s&target_lang=%s", paramText, paramSourceLang, paramTargetLang);
        logger.info("requestBody: " + requestBody);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Setting.getAsString("deepl.uri")))
                .version(HttpClient.Version.HTTP_2)
                .header("Authorization", "DeepL-Auth-Key " + Setting.getAsString("deepl.authorization"))
                .header("User-Agent", "translation/1.2.3")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        logger.info(Setting.getAsString("deepl.authorization"));

        try {
            // リクエストを送信
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonNode responseBody = mapper.readTree(response.body());
                String sourceLang = responseBody.get("translations").get(0).get("detected_source_language").asText();
                String translateResult = responseBody.get("translations").get(0).get("text").asText();

                System.out.printf("sourceLang: %s\r\n", sourceLang);
                System.out.printf("result: %s\r\n", translateResult);

                return translateResult;
            } else {
                return "なんらかのエラーが発生: HTTP STATUS: " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
