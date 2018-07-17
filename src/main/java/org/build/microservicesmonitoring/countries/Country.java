package org.build.microservicesmonitoring.countries;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private final String name;
    private final String code;

    @JsonCreator
    public Country(@JsonProperty("name") String name,
                   @JsonProperty("code") String code) {
        this.name = name;
        this.code = code;
    }
}
