package com.monkey999.service.translation;

import com.monkey999.constant.ResponseStatus;
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
import org.springframework.util.StringUtils;

@Service
public class TranslationService {
    Logger logger = LoggerFactory.getLogger(TranslationService.class);

    @Value("${setting.path:dummy}")
    public String settingPath;

    @Autowired
    TranslationClientFactory translationClientFactory;

    public TranslationRes translate(TranslationReq req) throws Exception {
        Setting.init(settingPath);

        // return
        var ret = new TranslationRes();

        // 翻訳APIコール
        String result = "";
        if (StringUtils.hasText(req.getSource()) && StringUtils.hasText(req.getTarget())) {
            if (TargetLang.JAPANESE.languageCode.equals(req.getSource())) {
                result = translationClientFactory.getInstance(req.getTranslationClient()).request(req.getText(), TargetLang.JAPANESE, TargetLang.ENGLISH);
                // kuso
                ret.setSource(TargetLang.JAPANESE);
                ret.setTarget(TargetLang.ENGLISH);
            } else {
                result = translationClientFactory.getInstance(req.getTranslationClient()).request(req.getText(), TargetLang.ENGLISH, TargetLang.JAPANESE);
                ret.setSource(TargetLang.ENGLISH);
                ret.setTarget(TargetLang.JAPANESE);
            }
        } else {
            result = translationClientFactory.getInstance(req.getTranslationClient()).request(req.getText());
            // TODO: target_lang設定
        }

        ret.setText(result);
        ret.setStatus(ResponseStatus.success);
        return ret;

    }
}