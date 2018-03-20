package com.cxy.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.cxy.service.IJestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.ScrollableHitSource;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import org.elasticsearch.index.reindex.ScrollableHitSource.Hit;


import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by lidongpeng on 2018/3/5.
 */
@Service
public class JestService implements IJestService{

    /**
     * 获取JestClient对象
     * @return
     */
    public RestHighLevelClient getJestClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("47.95.239.247",9200, "http")));
        return client;
    }

    @Override
    public BulkResponse createIndex(RestHighLevelClient jestClient, String indexName) throws Exception {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts", "doc", "1")
                .source(XContentType.JSON,"field", "foo"));
        BulkResponse bulkResponse = jestClient.bulk(request);
        return bulkResponse;
    }

    @Override
    public boolean createIndexMapping(RestHighLevelClient jestClient, String indexName, String typeName, String source) throws Exception {
        return false;
    }

    @Override
    public String getIndexMapping(RestHighLevelClient jestClient, String indexName, String typeName) throws Exception {
        return null;
    }

    @Override
    public boolean index(RestHighLevelClient jestClient, String indexName, String typeName, Object obj) throws Exception {
        IndexRequest request = new IndexRequest(
                indexName,
                typeName,
                UUID.randomUUID().toString());
        request.source(JSONObject.toJSONString(obj), XContentType.JSON);
        IndexResponse indexResponse = jestClient.index(request);
        indexResponse.status();
        return true;
    }

    @Override
    public SearchResponse search(RestHighLevelClient jestClient, String indexName, String typeName, String query) throws Exception {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.types(typeName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.simpleQueryStringQuery(query));

        sourceBuilder.from(0);
        sourceBuilder.size(5);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=jestClient.search(searchRequest);
        SearchHits searchHits=searchResponse.getHits();
        for (SearchHit hit: searchHits ) {
            System.out.println(hit.getSourceAsString());
            hit.getSourceAsString();
        }
        return searchResponse;
    }

    @Override
    public Double count(RestHighLevelClient jestClient, String indexName, String typeName, String query) throws Exception {
        return null;
    }

    @Override
    public RestHighLevelClient get(RestHighLevelClient jestClient, String indexName, String typeName, String id) throws Exception {
        return null;
    }

    @Override
    public boolean delete(RestHighLevelClient jestClient, String indexName) throws Exception {
        return false;
    }

    @Override
    public boolean delete(RestHighLevelClient jestClient, String indexName, String typeName, String id) throws Exception {
        return false;
    }

    @Override
    public void closeJestClient(RestHighLevelClient jestClient) throws Exception {

    }


}