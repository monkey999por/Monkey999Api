package com.monkey999.ent.common;

import java.beans.ConstructorProperties;

public class Certification {

    private String apiKey;

    @ConstructorProperties({"api_key"})
    public Certification(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
