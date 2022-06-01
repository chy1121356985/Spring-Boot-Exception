package com.renderg.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskStr implements Comparable<DiskStr> {

    @JsonProperty("id")
    private String id;

    @JsonProperty("DiskStr")
    private String diskStr;


    @Override
    public int compareTo(DiskStr disk) {
        try {
            if (this.diskStr.contains("Bytes")) {
                int anInt = Double.valueOf(this.diskStr.substring(0, this.diskStr.indexOf("B") - 1)).intValue();
                int anInt1 = Double.valueOf(disk.getDiskStr().substring(0, disk.getDiskStr().indexOf("B") - 1)).intValue();
                return anInt - anInt1;
            } else if (disk.getDiskStr().contains("GB")) {
                int anInt = Double.valueOf(this.diskStr.substring(0, this.diskStr.indexOf("G") - 1)).intValue();
                int anInt1 = Double.valueOf(disk.getDiskStr().substring(0, disk.getDiskStr().indexOf("G") - 1)).intValue();
                return anInt - anInt1;
            } else if (disk.getDiskStr().contains("MB")) {
                int anInt = Double.valueOf(this.diskStr.substring(0, this.diskStr.indexOf("M") - 1)).intValue();
                int anInt1 = Double.valueOf(disk.getDiskStr().substring(0, disk.getDiskStr().indexOf("M") - 1)).intValue();
                return anInt - anInt1;
            }

        } catch (Exception e) {
        }
        return 0;

    }
}