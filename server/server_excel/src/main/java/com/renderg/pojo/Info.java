package com.renderg.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    @JsonProperty("id")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("LastRenderTime")
    private Date lastRenderTime;

    @JsonProperty("RAMFree")
    private Long ramFree;

    @JsonProperty("DiskStr")
    private String diskStr;

    @JsonProperty("CPU")
    private String cpu;

    @JsonProperty("OS")
    private String os;

    @JsonProperty("Pools")
    private String pools;

    @JsonProperty("RAM")
    private Long ram;

    @JsonProperty("Grps")
    private String grps;

    @JsonProperty("Stat")
    private Integer stat;


}
