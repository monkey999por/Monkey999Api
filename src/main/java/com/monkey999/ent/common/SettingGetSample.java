package com.monkey999.ent.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SettingGetSample {

    @Value("${setting.path:dummy}")
    public String path;

}
