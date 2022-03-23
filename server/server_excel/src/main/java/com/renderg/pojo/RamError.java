package com.renderg.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RamError {

    private String id;
    private Long agoRam;
    private Long ram;
}
