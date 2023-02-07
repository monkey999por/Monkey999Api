package com.monkey999.utils.tool;

public class LangDetectorFactory {
    private LangDetectorFactory(){};

    public static LangDetector newInstance(){
        return new LangDetectorOfCybozuLabs();
    }
}
