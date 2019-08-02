package com.dayainfo.ssp.index;

import com.dayainfo.ssp.constant.CommonConstant;
import com.dayainfo.ssp.model.TableIndexMappingModel;
import com.dayainfo.ssp.util.PropertiesUtil;
import com.dayainfo.ssp.util.StringUtil;
import com.dayainfo.ssp.yaml.YAMlRead;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import static java.net.InetAddress.getByName;

/**
 * @Author: longrui
 * @text: 2018/4/19 18:09
 */
public class ESIndexFactory {
    //集群名,默认值elasticsearch
    private static String ES_CLUSTER_NAME;
    //ES集群中某个节点
    private static String ES_HOSTNAME;
    //连接端口号
    private static int ES_TCP_PORT;
    //TransportClient对象，用于连接ES集群
    private static volatile TransportClient client;

    static {
        Map<Object, Object> esmap = PropertiesUtil.readMulProperties("/application.properties");
        ES_CLUSTER_NAME = esmap.get("es_cluster_name").toString();
        ES_HOSTNAME = esmap.get("es_hostname").toString();
        ES_TCP_PORT = Integer.valueOf(esmap.get("es_tcp_port").toString());
    }

    /**
     * 内部类实现单例模式，只初始化一次
     *
     * @return
     * @throws UnknownHostException
     */
    public static TransportClient getClientInstance() {
        return ESTransportClient.client;
    }

    static class ESTransportClient {
        static TransportClient client;

        static {
            try {
                Settings settings = Settings.builder().put("cluster.name", ES_CLUSTER_NAME).build();
                client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(getByName(ES_HOSTNAME), ES_TCP_PORT));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取集群管理的ClusterAdminClient对象
     */
    public static ClusterAdminClient getClusterAdminClient() {
        return getClientInstance().admin().cluster();
    }

    /**
     * 获取索引管理的IndicesAdminClient
     */
    public static IndicesAdminClient getAdminClient() {
        return getClientInstance().admin().indices();
    }

    /**
     * 判定索引是否存在
     *
     * @param indexName
     * @return
     */
    public static boolean isExists(String indexName) {
        IndicesExistsResponse response = getAdminClient().prepareExists(indexName).get();
        return response.isExists() ? true : false;
    }

    /**
     * 创建索引
     *
     * @param indexName
     * @return
     */
    public static boolean createIndex(String indexName) throws Exception {
        CreateIndexResponse createIndexResponse = getAdminClient()
                .prepareCreate(indexName.toLowerCase())
                .get();
        setMapping(indexName);
        return createIndexResponse.isAcknowledged() ? true : false;
    }

    /**
     * 创建索引
     *
     * @param indexName 索引名
     * @param shards    分片数
     * @param replicas  副本数
     * @return
     */
    public static boolean createIndex(String indexName, int shards, int replicas) throws Exception {
        Settings settings = Settings.builder()
                .put("index.number_of_shards", shards)
                .put("index.number_of_replicas", replicas)
                .build();
        CreateIndexResponse createIndexResponse = getAdminClient()
                .prepareCreate(indexName.toLowerCase())
                .setSettings(settings)
                .execute().actionGet();
        setMapping(indexName);
        return createIndexResponse.isAcknowledged() ? true : false;
    }

    /**
     * 位索引indexName设置mapping
     */
    public static void setMapping(String indices) throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties");
        Map<String, List<TableIndexMappingModel>> listMap = YAMlRead.getTabbleIndexMap();
        List<TableIndexMappingModel> tableIndexMappingList = listMap.get(indices);
        for (TableIndexMappingModel tableIndexMappingModel : tableIndexMappingList) {
            if (!StringUtil.isEmpty(tableIndexMappingModel.getIndex_field_name())) {
                builder.startObject(tableIndexMappingModel.getIndex_field_name());
                if (!StringUtil.isEmpty(tableIndexMappingModel.getIndex_field_type())) {
                    builder.field("type", tableIndexMappingModel.getIndex_field_type());
                }
                if (!StringUtil.isEmpty(tableIndexMappingModel.getIndex_analyzer())) {
                    builder.field("analyzer", tableIndexMappingModel.getIndex_analyzer());
                }
                if (!StringUtil.isEmpty(tableIndexMappingModel.getFielddata())) {
                    builder.field("fielddata", tableIndexMappingModel.getFielddata());
                }
                builder.endObject();
            }
        }
        builder.endObject().endObject();
        PutMappingRequest mapping = Requests.putMappingRequest(indices).type(CommonConstant.INDEX_TYPE).source(builder);
        getAdminClient().putMapping(mapping).actionGet();
    }

    /**
     * 删除索引
     *
     * @param indexName
     * @return
     */
    public static boolean deleteIndex(String indexName) {
        AcknowledgedResponse deleteResponse = getAdminClient()
                .prepareDelete(indexName.toLowerCase())
                .execute()
                .actionGet();
        return deleteResponse.isAcknowledged() ? true : false;
    }
}