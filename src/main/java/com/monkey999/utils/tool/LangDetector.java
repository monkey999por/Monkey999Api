package com.monkey999.utils.tool;

import com.cybozu.labs.langdetect.Detector;
import com.monkey999.constant.TargetLang;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface LangDetector {
    /**
     * detect args text language.
     *
     * @param text detect target.
     */
    TargetLang detect(String text);

    /**
     * detect args text language.
     *
     * @param text detect target.
     * @return {@link Detector#getProbabilities()}
     */
    ArrayList<TargetLang> detects(String text);

    /**
     * @param text detect args text language.
     * @return is japanese ?
     */
    Boolean isJapanese(String text);
}
