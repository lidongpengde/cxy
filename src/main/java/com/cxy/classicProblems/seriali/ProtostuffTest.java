package com.cxy.classicProblems.seriali;

import com.alibaba.fastjson.JSONObject;
import com.cxy.entity.LineInfo;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by lidongpeng on 2018/5/3.
 */
public class ProtostuffTest {

    static RuntimeSchema<LineInfo> poSchema = RuntimeSchema.createFrom(LineInfo.class);

    static  LinkedBuffer buffer = LinkedBuffer.allocate(512);

    private static byte[] decode(LineInfo lineInfo){
        return ProtostuffIOUtil.toByteArray(lineInfo, poSchema, buffer);
    }

    private static LineInfo ecode(byte[] bytes){
        LineInfo po = poSchema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, po, poSchema);
        return po;
    }

    public static void main(String[] args) {
        LineInfo po = new LineInfo();
        po.setStart("13213213");
        po.setUserNickname("asdkasjdk");
        long stratprototime=System.currentTimeMillis();
        byte[] bytes = decode(po);
        System.out.println(System.currentTimeMillis()-stratprototime);
        long stratfastjson=System.currentTimeMillis();
        String aa=JSONObject.toJSONString(po);
        System.out.println(System.currentTimeMillis()-stratfastjson);
        LineInfo newPo = ecode(bytes);
        System.out.println(newPo);
    }
}
