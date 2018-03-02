package com.cxy.common;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lidongpeng on 2018/3/2.
 */
public class ESUtils {
    static JestClient client ;
    public static JestClient   ceateClient(){
        // Construct a new Jest client according to configuration via factory
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://10.58.62.243:9200")
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(2)
                // and no more 20 connections in total
                .maxTotalConnection(20)
                .build());
        if (client==null){
            client = factory.getObject();
        }
        return client;
    }
    @Test
    public void create() throws IOException {
        JestClient client= ceateClient();
        //Creating an Index
        Map<String, String> source = new LinkedHashMap<String,String>();
        source.put("user", "kimchy");
        Index index = new Index.Builder(source).index("go366").type("tweet").id("1").build();
        client.execute(index);
    }
    @Test
    public void search() throws IOException {
        JestClient client= ceateClient();
        Get get = new Get.Builder("go366","1").type("tweet").build();
        JestResult result = client.
                execute(get);
        System.out.println(result);
        // Article article = result.getSourceAsObject(Article.class);
    }
}
