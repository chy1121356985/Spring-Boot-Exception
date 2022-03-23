package com.renderg.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskStr {

    @JsonProperty("id")
    private String id;

    @JsonProperty("DiskStr")
    private String diskStr;
}
