package com.renderg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "SlaveInfo")
public class SlaveInfoMongo implements Comparable<SlaveInfoMongo> {

    @Field("_id")
    private String id;

    @Field("RAMFree")
    private Long ramFree;

    @Field("DiskStr")
    private String diskStr;

    @Field("RAM")
    private Long ram;

    @Field("Stat")
    private Integer stat;

    @Override
    public int compareTo(SlaveInfoMongo disk) {
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
