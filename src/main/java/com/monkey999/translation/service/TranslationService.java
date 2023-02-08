package com.monkey999.translation.service;

import com.monkey999.translation.client.TranslationClient;
import com.monkey999.translation.client.TranslationClientFactory;
import com.monkey999.translation.ent.TranslationReq;
import com.monkey999.translation.ent.TranslationRes;
import com.monkey999.utils.ent.SettingGetSample;
import com.monkey999.utils.tool.LangDetector;
import com.monkey999.utils.tool.LangDetectorFactory;
import com.monkey999.utils.tool.LangDetectorOfCybozuLabs;
import monkey999.tools.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TranslationService {
    Logger logger = LoggerFactory.getLogger(TranslationService.class);

    @Value("${setting.path:dummy}")
    public String settingPath;

    @Autowired
    LangDetector langDetector;

    public TranslationRes translate(TranslationReq req) {

        try{
            // TODO loggerを入れる

            // localはOK
            // ラズパイデプロイするとダメ？
            logger.info("setting start");
//            String path = ResourceUtils.getFile("classpath:translation/setting.properties").getPath();
            logger.info(settingPath);
            Setting.init(settingPath);
            logger.info(Setting.getAllToString());
            logger.info("setting done");

            // TODO: test Detector使えるか
            // localはOK
            // ラズパイデプロイするとダメ？
            logger.info("detector start");
            logger.info("ja: " + langDetector.isJapanese("あいうえお").toString());
            logger.info("ja: " + langDetector.isJapanese("this is pen").toString());
            logger.info("detector done");

            logger.info("api start");
//            TranslationClient client = TranslationClientFactory.newInstance();
            var result = new TranslationRes();
//            result.text = client.request(req.text);
            result.text = "client.request(req.text);";
            result.status = "200";
            logger.info("monkey999 api ok");
            return result;

        }catch (Exception e) {

//             TODO ラズパイに載せたら以下のエラー
//            cannot be resolved to absolute file path because it does not reside in the file system: jar:file:/server/Monkey999Api-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/translation/setting.properties

            logger.info("monkey999 api error");
            logger.info(e.getMessage());
            logger.info(e.getLocalizedMessage());
            e.printStackTrace();
            var temp = new TranslationRes();
            temp.status = "500";
            return temp;
        }




    }

}