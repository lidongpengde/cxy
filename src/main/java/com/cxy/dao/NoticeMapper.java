package com.cxy.dao;

import com.cxy.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    List getMessByUserAndCla(@Param("userid")String userid,@Param("classes") String classes);
    void updById(@Param("id") Long id);
}
