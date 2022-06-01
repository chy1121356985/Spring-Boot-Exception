package com.renderg.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskStrMB implements Comparable<DiskStrMB> {


    @JsonProperty("id")
    private String id;

    @JsonProperty("DiskStr")
    private String diskStr;


    @Override
    public int compareTo(DiskStrMB disk) {
        try {
            if (this.diskStr.contains("MB")) {
                int anInt = Double.valueOf(this.diskStr.substring(0, this.diskStr.indexOf("M") - 1)).intValue();
                int anInt1 = Double.valueOf(disk.getDiskStr().substring(0, disk.getDiskStr().indexOf("M") - 1)).intValue();
                return anInt - anInt1;
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
