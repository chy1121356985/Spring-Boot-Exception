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

    @JsonProperty("Stat")
    private Integer stat;


    @Override
    public int compareTo(DiskStr disk) {
        try {
            if (this.diskStr.contains("Bytes")) {
                int anInt = Double.valueOf(this.diskStr.substring(0, this.diskStr.indexOf("B") - 1)).intValue();
                int anInt1 = Double.valueOf(disk.getDiskStr().substring(0, disk.getDiskStr().indexOf("B") - 1)).intValue();
                int by= anInt - anInt1;
                System.out.println("byte:"+by);
                return by;
            } else if (disk.getDiskStr().contains("GB")) {
                int anInt = Double.valueOf(this.diskStr.substring(0, this.diskStr.indexOf("G") - 1)).intValue();
                int anInt1 = Double.valueOf(disk.getDiskStr().substring(0, disk.getDiskStr().indexOf("G") - 1)).intValue();
                int gb=anInt - anInt1;
                System.out.println("gb:"+gb+"---"+this.diskStr+"--"+disk.getDiskStr());
                return gb;
            } else if (disk.getDiskStr().contains("MB")) {
                int anInt = Double.valueOf(this.diskStr.substring(0, this.diskStr.indexOf("M") - 1)).intValue();
                int anInt1 = Double.valueOf(disk.getDiskStr().substring(0, disk.getDiskStr().indexOf("M") - 1)).intValue();
                int mb=anInt - anInt1;
                System.out.println("mb"+mb);
                return mb;
            }

        } catch (Exception e) {
        }
        return 0;

    }
}