package com.dayainfo.ssp.service;

import com.dayainfo.ssp.index.ESHighlight;
import com.dayainfo.ssp.index.ESIndexFactory;
import com.dayainfo.ssp.model.IndexResourceInfoModel;
import com.dayainfo.ssp.util.ReflectionUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: longrui
 * @Date: 2018/4/23 15:52
 * 搜索记录并且获取聚类
 */
public class SearchESIndexService {
    private static Logger logger = LoggerFactory.getLogger(SearchESIndexService.class);
    private static int pageSize = 10;
    private static TransportClient client = null;

    public static void main(String[] args) {
        try {
            init();
            searchES("ss_ware_resource_info");
        } catch (Exception e) {
            logger.error("出错了，错误信息如下：");
            e.printStackTrace();
        }
    }

    public static void init() {
        //web方式启动的时候，client对象放到initServerlet里面初始化，这个client对象获取耗时较多
        long begin = System.currentTimeMillis();
        client = ESIndexFactory.getClientInstance();
        logger.info("获取client对象耗时：" + (System.currentTimeMillis() - begin) + "毫秒");
    }

    /**
     * ES搜索
     */
    public static void searchES(String indexName) throws Exception {
        long begin = System.currentTimeMillis();

        ESHighlight esHighlight = new ESHighlight();
        List<String> highlightFieldList = esHighlight.setHighlightField(Arrays.asList(new String[]{"basic_description", "basic_id_contact"}));
        List<String> groupList = new ArrayList<String>(Arrays.asList(new String[]{"year", "basic_id_contact_simple", "basic_id_creator_simple", "basic_id_keyword_simple"}));

        SearchHits searchHits = getSearchHits(client, esHighlight, groupList, indexName);

        SearchHit[] hits = searchHits.getHits();
        logger.info("搜索到记录条数：" + hits.length);

        List<IndexResourceInfoModel> indexResourceInfoModelList = new ArrayList<IndexResourceInfoModel>();
        getResultList(highlightFieldList, hits, indexResourceInfoModelList);

        logger.info("【分页搜索结果详情】");
        for (IndexResourceInfoModel indexResourceInfoModel : indexResourceInfoModelList) {
            logger.info(indexResourceInfoModel.toString());
        }
        logger.info("搜索耗时：" + (System.currentTimeMillis() - begin) + "毫秒");
    }

    //设置高亮，排序，分组，分页
    private static SearchHits getSearchHits(TransportClient client, ESHighlight esHighlight, List<String> groupList, String indexName) {
        //配置搜索条件
        QueryBuilder qb = getQueryBuilder();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName).setQuery(qb);
        //分组
        for (String group : groupList) {
            searchRequestBuilder.addAggregation(AggregationBuilders.terms(group).field(group));
        }
        SearchResponse response =
                //高亮
                searchRequestBuilder.highlighter(esHighlight.getHiBuilder())
                        //排序
                        .addSort("year", SortOrder.DESC)
                        //分页，size:一页多少记录  from：起始位置
                        .setSize(pageSize).setFrom(0)
                        .execute().actionGet();
        for (String group : groupList) {
            TermsCollect(response, group);
        }
        return response.getHits();
    }

    private static void TermsCollect(SearchResponse response, String keywordTerm) {
        Terms terms = response.getAggregations().get(keywordTerm);
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        logger.info("【" + keywordTerm + "分组情况】");
        for (Terms.Bucket bucket : buckets) {
            logger.info(bucket.getKey() + " " + bucket.getDocCount());
        }
        logger.info("");
    }

    //boolQuery 组合查询，可以与(must)或(should)非(mustnot)
    private static QueryBuilder getQueryBuilder() {
        return QueryBuilders.boolQuery()
                //termQuery 模糊查询
                .must(QueryBuilders.termQuery("basic_id_contact_IK", "海南"))
                //                .must(QueryBuilders.termQuery("basic_id_creator_simple", "郭秀凤"))
                .must(QueryBuilders.termQuery("basic_description_IK", "中国"))
                .must(QueryBuilders.termQuery("basic_id_keyword_IK", "五指山市"));
    }

    //搜索出来的记录放到IndexResourceInfoModel对象里面去
    private static void getResultList(List<String> highlightFieldList, SearchHit[] hits, List<IndexResourceInfoModel> indexResourceInfoModelList) {
        for (SearchHit hit : hits) {
            IndexResourceInfoModel indexResourceInfoModel = new IndexResourceInfoModel();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            for (Map.Entry<String, Object> stringObjectEntry : sourceAsMap.entrySet()) {
                String fieldName = stringObjectEntry.getKey();
                if (highlightFieldList.contains(fieldName)) {
                    StringBuilder sb = new StringBuilder();
                    Text[] text = hit.getHighlightFields().get(fieldName).getFragments();
                    for (Text str : text) {
                        sb.append(str.string());
                    }
                    ReflectionUtil.setFieldValue(indexResourceInfoModel, fieldName, sb.toString());
                } else {
                    ReflectionUtil.setFieldValue(indexResourceInfoModel, fieldName, stringObjectEntry.getValue());
                }
            }
            indexResourceInfoModelList.add(indexResourceInfoModel);
        }
    }

}
