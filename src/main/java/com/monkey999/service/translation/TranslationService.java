package com.monkey999.service.translation;

import com.monkey999.constant.TargetLang;
import com.monkey999.ent.interfaces.translation.TranslationReq;
import com.monkey999.ent.interfaces.translation.TranslationRes;
import com.monkey999.utils.external.api.client.TranslationClientFactory;
import monkey999.tools.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {
    Logger logger = LoggerFactory.getLogger(TranslationService.class);

    @Value("${setting.path:dummy}")
    public String settingPath;

    @Autowired
    TranslationClientFactory translationClientFactory;

    public TranslationRes translate(TranslationReq req) {

        logger.info(req.toString());

        try {
            logger.info(settingPath);
            Setting.init(settingPath);
            logger.info(Setting.getAllToString());

            var result = new TranslationRes();
            result.setText(translationClientFactory.getInstance().request(req.getText()));

            return result;
        } catch (Exception e) {
            logger.info("monkey999 api error");
            logger.info(e.getMessage());
            logger.info(e.getLocalizedMessage());
            var res = new TranslationRes();

            return res;
        }
    }
}