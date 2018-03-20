package com.cxy.service;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lidongpeng on 2018/3/5.
 */
@Service
public interface IJestService {

    /**
     * 获取JestClient对象
     * @return
     */
    public RestHighLevelClient getJestClient();

    /**
     * 创建索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws Exception
     */
    public BulkResponse createIndex(RestHighLevelClient jestClient, String indexName) throws Exception;

    /**
     * Put映射
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param source
     * @return
     * @throws Exception
     */
    public boolean createIndexMapping(RestHighLevelClient jestClient, String indexName, String typeName, String source) throws Exception;

    /**
     * Get映射
     * @param jestClient
     * @param indexName
     * @param typeName
     * @return
     * @throws Exception
     */
    public String getIndexMapping(RestHighLevelClient jestClient, String indexName, String typeName) throws Exception ;

    /**
     * 索引文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @return
     * @throws Exception
     */
    public boolean index(RestHighLevelClient jestClient, String indexName, String typeName,Object obj) throws Exception ;

    /**
     * 搜索文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public SearchResponse search(RestHighLevelClient jestClient, String indexName, String typeName, String query) throws Exception;

    /**
     * Count文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public Double count(RestHighLevelClient jestClient, String indexName, String typeName, String query) throws Exception ;

    /**
     * Get文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public RestHighLevelClient get(RestHighLevelClient jestClient, String indexName, String typeName, String id) throws Exception ;

    /**
     * Delete索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws Exception
     */
    public boolean delete(RestHighLevelClient jestClient, String indexName) throws Exception ;
    /**
     * Delete文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(RestHighLevelClient jestClient, String indexName, String typeName, String id) throws Exception ;
    /**
     * 关闭JestClient客户端
     * @param jestClient
     * @throws Exception
     */
    public void closeJestClient(RestHighLevelClient jestClient) throws Exception ;
}