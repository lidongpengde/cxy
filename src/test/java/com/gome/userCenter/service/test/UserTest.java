package com.gome.userCenter.service.test;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.*;
import com.cxy.entity.*;
import com.cxy.service.IJestService;
import com.cxy.service.ILineInfoService;
import com.cxy.service.Impl.JestService;
import com.cxy.service.IuserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.action.support.QuerySourceBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lidongpeng on 2018/3/5.
 */
public class UserTest {

    private JestService jestService;
    private JestClient jestClient;
    private String indexName = "hwd";
    private String typeName = "user";

    @Before
    public void setUp() throws Exception {

        jestService = new JestService();
        jestClient = jestService.getJestClient();
    }

    @After
    public void tearDown() throws Exception {

        jestService.closeJestClient(jestClient);
    }

    @Test
    public void createIndex() throws Exception {

        boolean result = jestService.createIndex(jestClient, indexName);
        System.out.println(result);
    }

    @Test
    public void createIndexMapping() throws Exception {

        String source = "{\"" + typeName + "\":{\"properties\":{"
                + "\"id\":{\"type\":\"integer\"}"
                + ",\"name\":{\"type\":\"string\",\"index\":\"not_analyzed\"}"
                + ",\"birth\":{\"type\":\"date\",\"format\":\"strict_date_optional_time||epoch_millis\"}"
                + "}}}";
        System.out.println(source);
        boolean result = jestService.createIndexMapping(jestClient, indexName, typeName, source);
        System.out.println(result);
    }

    @Test
    public void getIndexMapping() throws Exception {

        String result = jestService.getIndexMapping(jestClient, indexName, typeName);
        System.out.println(result);
    }

    @Test
    public void index() throws Exception {

        List<Object> objs = new ArrayList<Object>();
        objs.add(new User());
        objs.add(new User());
        boolean result = jestService.index(jestClient, indexName, typeName, objs);
        System.out.println(result);
    }

    @Test
    public void termQuery() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .termQuery("name", "T:o\"m-");//单值完全匹配查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        SearchResult result = jestService.search(jestClient, indexName, typeName, query);
        List<Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }

    @Test
    public void termsQuery() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .termsQuery("name", new String[]{ "T:o\"m-", "J,e{r}r;y:" });//多值完全匹配查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        SearchResult result = jestService.search(jestClient, indexName, typeName, query);
        List<Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }

    @Test
    public void wildcardQuery() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name", "*:*");//通配符和正则表达式查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        SearchResult result = jestService.search(jestClient, indexName, typeName, query);
        List<Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }

    @Test
    public void prefixQuery() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .prefixQuery("name", "T:o");//前缀查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        SearchResult result = jestService.search(jestClient, indexName, typeName, query);
        List<Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }

    @Test
    public void rangeQuery() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .rangeQuery("birth")
                .gte("2016-09-01T00:00:00")
                .lte("2016-10-01T00:00:00")
                .includeLower(true)
                .includeUpper(true);//区间查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        SearchResult result = jestService.search(jestClient, indexName, typeName, query);
        List<Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }

    @Test
    public void queryString() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .queryStringQuery(QueryParser.escape("T:o\""));//文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        SearchResult result = jestService.search(jestClient, indexName, typeName, query);
        List<Hit<User, Void>> hits = result.getHits(User.class);
        System.out.println("Size:" + hits.size());
        for (Hit<User, Void> hit : hits) {
            User user = hit.source;
            System.out.println(user.toString());
        }
    }

    @Test
    public void count() throws Exception {

        String[] name = new String[]{ "T:o\"m-", "Jerry" };
        String from = "2016-09-01T00:00:00";
        String to = "2016-10-01T00:00:00";
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termsQuery("name", name))
                .must(QueryBuilders.rangeQuery("birth").gte(from).lte(to));
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Double count = jestService.count(jestClient, indexName, typeName, query);
        System.out.println("Count:" + count);
    }

    @Test
    public void get() throws Exception {

        String id = "2";
        JestResult result = jestService.get(jestClient, indexName, typeName, id);
        if (result.isSucceeded()) {
            User user = result.getSourceAsObject(User.class);
            System.out.println(user.toString());
        }
    }

    @Test
    public void deleteIndexDocument() throws Exception {

        String id = "2";
        boolean result = jestService.delete(jestClient, indexName, typeName, id);
        System.out.println(result);
    }

    @Test
    public void deleteIndex() throws Exception {

        boolean result = jestService.delete(jestClient, indexName);
        System.out.println(result);
    }
}
