package com.monkey999.utils.tool;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.monkey999.constant.TargetLang;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * detect language.
 */
@Component
public class LangDetectorOfCybozuLabs implements LangDetector {

    @Value("${lang.detector.profile:dummy}")
    String langDetectorProfile;

    /**
     * Status of {@link com.cybozu.labs.langdetect}
     */
    private static class Status {
        // is init? changed by constructor only.
        private static boolean isInit = false;
    }

    /**
     * init.
     *
     * @throws LangDetectException see {@link DetectorFactory#loadProfile(String)}
     */

    public LangDetectorOfCybozuLabs(@Value("${lang.detector.profile:dummy}") String profile) {
        synchronized (this) {
            try {
                if (!Status.isInit) {
//                    String profile = Paths.get(Setting.getAsString("lang_detector_profile")).toFile().getAbsolutePath();
//                    String profile = ResourceUtils.getFile("classpath:" + Setting.getAsString("lang_detector_profile")).getPath();
                    DetectorFactory.loadProfile(profile);
                    Status.isInit = true;
                }
            } catch (Exception e) {
                // todo
            }
        }
    }


    /**
     * detect args text language.
     *
     * @param text detect target.
     * @return {@link Detector#detect()}
     * @throws LangDetectException see {@link DetectorFactory#create()}
     */
    @Override
    public TargetLang detect(String text) {
        try {

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
        } catch (Exception e) {
            e.printStackTrace();
            return TargetLang.JAPANESE;
        }
    }

    /**
     * detect args text language.
     *
     * @param text detect target.
     * @return {@link Detector#getProbabilities()}
     * @throws LangDetectException see {@link DetectorFactory#create()}
     */
    @Override
    public ArrayList<TargetLang> detects(String text) {
        try {
            Detector detector = DetectorFactory.create();
            detector.append(text);
            // return detector.getProbabilities();
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @param text detect args text language.
     * @return is japanese ?
     * @throws LangDetectException see {@link DetectorFactory#create()}
     */
    @Override
    public Boolean isJapanese(String text) {
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
