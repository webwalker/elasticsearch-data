package com.dayainfo.ssp.yaml;

import com.dayainfo.ssp.model.TableIndexMappingModel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: longrui
 * @Date: 2018/4/28 11:06
 */
public class YAMlRead {

    private static Logger logger = LoggerFactory.getLogger(YAMlRead.class);
    @Setter
    @Getter
    private static Map<String, List<TableIndexMappingModel>> tabbleIndexMap = new HashMap<String, List<TableIndexMappingModel>>();

    public static void main(String[] args) throws FileNotFoundException {
        readInfo();
        for (Map.Entry<String, List<TableIndexMappingModel>> entry : tabbleIndexMap.entrySet()) {
            logger.info("【" + entry.getKey() + "】配置属性为：");
            for (TableIndexMappingModel indexMapping : entry.getValue()) {
                logger.info(indexMapping + "");
            }
        }
    }

    //读取yaml配置文件
    public static void readInfo() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        List<File> yamlFiles = new ArrayList<File>();
        File dbConfFile = new File(YAMlRead.class.getResource("/dbConf").getPath());
        if (dbConfFile.isDirectory()) {
            File[] fileList = dbConfFile.listFiles();
            for (File file : fileList) {
                if (file.getName().indexOf(".yaml") > -1) {
                    yamlFiles.add(file);
                }
            }
        }
        for (File yamlFile : yamlFiles) {
            TableIndexMappingModel tableIndexMapping = (TableIndexMappingModel) yaml.loadAs(new FileInputStream(yamlFile), TableIndexMappingModel.class);
            List<TableIndexMappingModel> tableIndexMappingList = tableIndexMapping.getFieldList();
            tabbleIndexMap.put(yamlFile.getName().substring(0, yamlFile.getName().indexOf(".yaml")), tableIndexMappingList);
        }
    }
}
