package com.monkey999.ent.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Certification {
    @JsonProperty("api_key")
    private String apiKey;


}
