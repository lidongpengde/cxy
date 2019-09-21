package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.dao.LocationMapper;
import com.cxy.entity.Location;
import com.cxy.entity.LocationInfo;
import com.mongodb.Bytes;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lidongpeng on 2017/8/17.
 */
@Controller
public class MongoHandler {
    LocationMapper locationMapper;

@RequestMapping("/mysql")
    public  String fromMongoToMysql() {
        MongoClient mongoClient = new MongoClient( "localhost" , 3001 );
        MongoDatabase database = mongoClient.getDatabase("meteor");
        MongoCollection<Document> collection = database.getCollection("tasks");
        int count=0;
        for (Document cur : collection.find().batchSize(30)) {
            if (count>=2956){
                System.out.println(1234);
            }
            String jsonString=cur.toJson();
            LocationInfo locationInfo=JSONObject.parseObject(jsonString,LocationInfo.class);
            Location location=new Location();
            location.setId(locationInfo.get_id());
            location.setCityCode(locationInfo.getCitycode());
            location.setAdCode(locationInfo.getAdcode());
            location.setName(locationInfo.getName());
            location.setLevel(locationInfo.getLevel());
            location.setLatitude(locationInfo.getCenter().get("lat"));
            location.setLongitude(locationInfo.getCenter().get("lng"));
            locationMapper.insert(location);
            count++;
        }
return null;
    }


}
