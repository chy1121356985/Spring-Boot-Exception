package com.renderg.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 响应实体
 *
 * @author kuazhen
 * @Date:Created in 3/17/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonContent {

    private List<Info> info;
    private List<Settings> settings;






    }

