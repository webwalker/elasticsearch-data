package com.dayainfo.ssp.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: longrui
 * @Date: 2018/4/28 10:42
 */
@Data
public class TableIndexMappingModel {

    private String table_field_name;
    private String index_field_name;
    private String index_field_type;
    private String index_analyzer;
    private String fielddata;
    private List<TableIndexMappingModel> fieldList;
}
