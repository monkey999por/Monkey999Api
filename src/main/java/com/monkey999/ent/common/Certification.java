package com.monkey999.ent.common;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class Certification {
    private String apiKey;

    @ConstructorProperties({"api_key"})
    public Certification(String apiKey) {
        this.apiKey = apiKey;
    }

}
