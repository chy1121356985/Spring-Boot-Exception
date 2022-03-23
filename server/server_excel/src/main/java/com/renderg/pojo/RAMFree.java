package com.renderg.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RAMFree {

    @JsonProperty("id")
    private String id;

    @JsonProperty("RAMFree")
    private Long ramFree;

    @JsonProperty("RAM")
    private Long ram;
}
