package com.monkey999.translation.service;

import com.monkey999.translation.client.TranslationClient;
import com.monkey999.translation.client.TranslationClientFactory;
import com.monkey999.translation.ent.TranslationReq;
import com.monkey999.translation.ent.TranslationRes;
import com.monkey999.utils.tool.LangDetector;
import com.monkey999.utils.tool.LangDetectorFactory;
import monkey999.tools.Setting;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class TranslationService {

    public TranslationRes translate(TranslationReq req) {

        try{
            // TODO: test 設定ファイルが読めるか
            String path = ResourceUtils.getFile("classpath:translation/setting.properties").getAbsolutePath();
            Setting.init(path);
            System.out.println(Setting.getAllToString());

            // TODO: test Detector使えるか
            LangDetector detector = LangDetectorFactory.newInstance();
            System.out.println(detector.isJapanese("あいうえお"));
            System.out.println(detector.isJapanese("apple"));

//            TranslationClient client = TranslationClientFactory.newInstance();
            var result = new TranslationRes();
//            result.text = client.request(req.text);
                        result.text = "client.request(req.text);";
            result.status = "200";
            return result;

        }catch (Exception e) {
            e.printStackTrace();
            var temp = new TranslationRes();
            temp.status = "500";
            return temp;
        }




    }

}