package com.renderg.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hardware {

    @JsonProperty("id")
    private String id;

    @JsonProperty("DiskStr")
    private String diskStr;

    @JsonProperty("RAMFree")
    private Long ramFree;

    @JsonProperty("RAM")
    private Long ram;

    @JsonProperty("Enable")
    private Boolean enable;

    @JsonProperty("Cmmt")
    private String cmmt;
}
