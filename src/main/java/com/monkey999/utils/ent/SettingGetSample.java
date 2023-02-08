package com.monkey999.utils.ent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class SettingGetSample {

    @Value("${setting.path:dummy}")
    public String path;

}
