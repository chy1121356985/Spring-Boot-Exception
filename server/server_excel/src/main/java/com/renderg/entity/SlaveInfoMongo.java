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
public class SlaveInfoMongo {

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

}
