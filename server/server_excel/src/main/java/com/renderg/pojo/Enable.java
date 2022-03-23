package com.renderg.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("Enable")
    private Boolean enable;

    @JsonProperty("Cmmt")
    private String cmmt;
}
