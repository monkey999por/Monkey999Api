package com.monkey999.service.translation;

import com.monkey999.constant.TargetLang;
import com.monkey999.ent.interfaces.ErrorRes;
import com.monkey999.ent.interfaces.base.BaseRes;
import com.monkey999.ent.interfaces.translation.TranslationReq;
import com.monkey999.ent.interfaces.translation.TranslationRes;
import com.monkey999.utils.external.api.client.TranslationClientFactory;
import monkey999.tools.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.annotation.Target;

@Service
public class TranslationService {
    Logger logger = LoggerFactory.getLogger(TranslationService.class);

    @Value("${setting.path:dummy}")
    public String settingPath;

    @Autowired
    TranslationClientFactory translationClientFactory;

    public BaseRes translate(TranslationReq req) {

        logger.info(req.toString());

        try {
            logger.info(settingPath);
            Setting.init(settingPath);
            logger.info(Setting.getAllToString());

            // 翻訳APIコール
            String result = "";
            if(StringUtils.hasText(req.getSource()) && StringUtils.hasText(req.getTarget())){
                if (TargetLang.JAPANESE.languageCode.equals(req.getSource())){
                    result = translationClientFactory.getInstance(req.getTranslationClient()).request(req.getText(), TargetLang.JAPANESE, TargetLang.ENGLISH);
                } else {
                    result = translationClientFactory.getInstance(req.getTranslationClient()).request(req.getText(), TargetLang.ENGLISH, TargetLang.JAPANESE);
                }

            } else {
                result = translationClientFactory.getInstance(req.getTranslationClient()).request(req.getText());
            }

            var ret = new TranslationRes();
            ret.setText(result);
            return ret;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ErrorRes();
        }
    }
}