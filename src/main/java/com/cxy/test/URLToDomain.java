package com.cxy.test;

import com.cxy.entity.User;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;


/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
    String aaa;
    /**
     * 根据url获取domain
     * @return
     * @throws IOException
     */

    public void parseDomain() throws IOException {
        User user = null;
        if (StringUtils.isEmpty(user.getMobile()))
            System.out.println("tinghaoder");

    }
    @Test
    public  void main() {
        String sql="LineInfo";
        sqlValidate(sql);


    }
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|create|drop|table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) !=-1) {
                return true;
            }
        }
        return false;
    }

}
