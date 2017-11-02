package com.cxy.dao;


import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;

import javax.sound.sampled.Line;
import java.util.List;

public interface LineInfoMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(LineInfo record);

    int insertSelective(LineInfo record);

    LineInfo selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(LineInfo record);

    int updateByPrimaryKey(LineInfo record);
    List<LineInfo> getLineInfoListForLocation (LineInfo record);
    List<LineInfoAndUserInfo> getLineInfoList(LineInfo record);
    List<LineInfoAndUserInfo> getSubLineInfoList(LineInfo lineInfo);
    int countLineInfo(LineInfo record);
    int countSubLineInfo(LineInfo record);
    int getLineInfoSubCount(Long  userId);
}