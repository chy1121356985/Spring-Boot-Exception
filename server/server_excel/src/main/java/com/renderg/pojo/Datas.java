package com.renderg.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datas {

    private Integer size;

    private HashMap<String, List<DiskStr>> diskStr;

    private HashMap<String, List<RAMFree>> ramMap;

    private HashMap<String, List<Enable>> enableMap;
}
