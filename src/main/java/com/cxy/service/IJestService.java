package com.cxy.service;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
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
    public JestClient getJestClient();

    /**
     * 创建索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws Exception
     */
    public boolean createIndex(JestClient jestClient, String indexName) throws Exception;

    /**
     * Put映射
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param source
     * @return
     * @throws Exception
     */
    public boolean createIndexMapping(JestClient jestClient, String indexName, String typeName, String source) throws Exception;

    /**
     * Get映射
     * @param jestClient
     * @param indexName
     * @param typeName
     * @return
     * @throws Exception
     */
    public String getIndexMapping(JestClient jestClient, String indexName, String typeName) throws Exception ;

    /**
     * 索引文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param objs
     * @return
     * @throws Exception
     */
    public boolean index(JestClient jestClient, String indexName, String typeName, List<Object> objs) throws Exception ;

    /**
     * 搜索文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public SearchResult search(JestClient jestClient, String indexName, String typeName, String query) throws Exception;

    /**
     * Count文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param query
     * @return
     * @throws Exception
     */
    public Double count(JestClient jestClient, String indexName, String typeName, String query) throws Exception ;

    /**
     * Get文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public JestResult get(JestClient jestClient, String indexName, String typeName, String id) throws Exception ;

    /**
     * Delete索引
     * @param jestClient
     * @param indexName
     * @return
     * @throws Exception
     */
    public boolean delete(JestClient jestClient, String indexName) throws Exception ;
    /**
     * Delete文档
     * @param jestClient
     * @param indexName
     * @param typeName
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(JestClient jestClient, String indexName, String typeName, String id) throws Exception ;
    /**
     * 关闭JestClient客户端
     * @param jestClient
     * @throws Exception
     */
    public void closeJestClient(JestClient jestClient) throws Exception ;
}