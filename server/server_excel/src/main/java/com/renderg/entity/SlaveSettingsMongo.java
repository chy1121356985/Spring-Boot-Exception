package com.renderg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "SlaveSettings")
public class SlaveSettingsMongo {

    @Field("_id")
    private String id;

    @Field("Enable")
    private Boolean enable;

    @Field("Cmmt")
    private String cmmt;

    @Field("Desc")
    private String desc;

    @Field("Ex3")
    private String ex3;
}
