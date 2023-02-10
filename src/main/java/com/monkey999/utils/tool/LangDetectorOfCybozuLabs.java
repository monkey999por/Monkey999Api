package com.monkey999.utils.tool;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.monkey999.constant.TargetLang;
import com.monkey999.utils.external.api.client.TranslationClientOfDeepL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * {@inheritDoc}
 */
@Component
public class LangDetectorOfCybozuLabs implements LangDetector {

    Logger logger = LoggerFactory.getLogger(TranslationClientOfDeepL.class);

    private static class Status {
        // is init? changed by constructor only.
        private static boolean isInit = false;
    }

    public LangDetectorOfCybozuLabs(@Value("${lang.detector.profile:dummy}") String profile) throws Exception {
        if (!Status.isInit) {
            DetectorFactory.loadProfile(profile);
            Status.isInit = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TargetLang detect(String text) throws Exception {

        logger.info("lang detector run.");

        Detector detector = DetectorFactory.create();
        detector.append(text);
        var result = detector.detect();
        switch (result) {
            case "ja":
                return TargetLang.JAPANESE;
            case "ko":
                return TargetLang.KOREA;
            case "zh-cn":
                return TargetLang.CHINESE;
            default:
                return TargetLang.ENGLISH;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<TargetLang> detects(String text) throws Exception {
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isJapanese(String text) throws Exception {
        switch (detect(text)) {
            case JAPANESE:
            case KOREA:
            case CHINESE:
                return true;
            default:
                return false;
        }
    }
}
