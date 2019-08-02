package com.dayainfo.ssp.service;

import com.alibaba.fastjson.JSON;
import com.dayainfo.ssp.constant.CommonConstant;
import com.dayainfo.ssp.index.ESIndexFactory;
import com.dayainfo.ssp.model.IndexResourceInfoModel;
import com.dayainfo.ssp.model.TableIndexMappingModel;
import com.dayainfo.ssp.util.JdbcTemplateUtil;
import com.dayainfo.ssp.util.StringUtil;
import com.dayainfo.ssp.yaml.YAMlRead;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: longrui
 * @Date: 2018/4/23 10:58
 * 创建索引
 */
public class CreateESIndexService {
    private static Logger logger = LoggerFactory.getLogger(CreateESIndexService.class);
    public static final int COUNT_SPLIT = 10000;
    private static String indexName;

    public static void main(String[] args) {
        try {
            init();
            addData2Index();
        } catch (Exception e) {
            logger.error("出错了，错误信息如下：");
            e.printStackTrace();
        }
    }

    public static void init() throws FileNotFoundException {
        //web方式启动的时候，放到initServerlet里面初始化
        YAMlRead.readInfo();
    }

    public static void addData2Index() throws Exception {
        Map<String, List<TableIndexMappingModel>> listMap = YAMlRead.getTabbleIndexMap();

        for (Map.Entry<String, List<TableIndexMappingModel>> entry : listMap.entrySet()) {
            indexName = entry.getKey();
            boolean indexIsExist = ESIndexFactory.isExists(indexName);
            if (!indexIsExist) {
                ESIndexFactory.createIndex(indexName);
            } else {
                ESIndexFactory.deleteIndex(indexName);
                ESIndexFactory.createIndex(indexName);
            }
            String countSql = "";
            StringBuilder resultSql = new StringBuilder();
            Class modelClass = IndexResourceInfoModel.class;
            countSql = "select count(*) from " + entry.getKey();
            resultSql.append("select ");
            for (int i = 0; i < entry.getValue().size(); i++) {
                TableIndexMappingModel tableIndexMappingModel = entry.getValue().get(i);
                if (!StringUtil.isEmpty(tableIndexMappingModel.getTable_field_name())) {
                    if (!StringUtil.isEmpty(tableIndexMappingModel.getIndex_field_name())) {
                        resultSql.append(tableIndexMappingModel.getTable_field_name()).append(" ").append(tableIndexMappingModel.getIndex_field_name());
                        if (i != entry.getValue().size() - 1) {
                            resultSql.append(",");
                        }
                    }else{
                        resultSql.append(tableIndexMappingModel.getTable_field_name()).append(" ").append(tableIndexMappingModel.getTable_field_name());
                        if (i != entry.getValue().size() - 1) {
                            resultSql.append(",");
                        }
                    }
                }
            }
            resultSql.append(" from ").append(entry.getKey()).append("  limit ?,?");
            createIndex(countSql, resultSql.toString(), modelClass);
        }
    }

    //创建索引
    private static void createIndex(String countSql, String resultSql, Class modelClass) throws Exception {
        long begin = System.currentTimeMillis();
        List<IndexResourceInfoModel> ssWareResourceInfoModelList = new ArrayList<IndexResourceInfoModel>();
        int count = JdbcTemplateUtil.queryCount(countSql, new Object[]{});
        logger.info("一共" + count + "条记录");
        if (count <= COUNT_SPLIT) {
            ssWareResourceInfoModelList = JdbcTemplateUtil.queryList(resultSql, modelClass, 0, count);
            addBatchResult2Index(ssWareResourceInfoModelList);
        } else {
            for (int i = 1; i < count + 1; i++) {
                int page = i / COUNT_SPLIT;
                if (count == i) {
                    ssWareResourceInfoModelList = JdbcTemplateUtil.queryList(resultSql, modelClass, page * COUNT_SPLIT, count - page * COUNT_SPLIT);
                    addBatchResult2Index(ssWareResourceInfoModelList);
                    logger.info("已索引" + count + "条记录");
                }
                if (i % COUNT_SPLIT == 0) {
                    //select unit_id unit_id,resource_id resource_id,res_ware_id res_ware_id_simple,title title_IK,basic_id_contact basic_id_contact_IK,basic_id_contact basic_id_contact_simple,
                    // basic_id_creator basic_id_creator_simple,basic_id_keyword basic_id_keyword_IK,basic_id_keyword basic_id_keyword_simple,year year,basic_description basic_description_IK
                    // from ss_ware_resource_info  limit ?,?
                    ssWareResourceInfoModelList = JdbcTemplateUtil.queryList(resultSql, modelClass, page * COUNT_SPLIT, COUNT_SPLIT);
                    addBatchResult2Index(ssWareResourceInfoModelList);
                    logger.info("已索引" + page * COUNT_SPLIT + "条记录");
                }
            }
        }
        logger.info("索引创建结束");
        logger.info("一共耗时：" + (System.currentTimeMillis() - begin) / 1000 + "秒");
    }

    //批处理添加记录到索引
    private static void addBatchResult2Index(List<IndexResourceInfoModel> ssWareResourceInfoModelList) throws IOException {
        TransportClient client = ESIndexFactory.getClientInstance();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (IndexResourceInfoModel ssWareResourceInfoModel : ssWareResourceInfoModelList) {
            String jsonStr = JSON.toJSONString(ssWareResourceInfoModel);
            bulkRequest.add(client.prepareIndex(indexName, CommonConstant.INDEX_TYPE).setSource(jsonStr, XContentType.JSON));
        }
        bulkRequest.get();
    }
}
