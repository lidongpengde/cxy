package com.cxy.dao;


import com.cxy.entity.LineInfo;

import java.util.List;

public interface LineInfoMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(LineInfo record);

    int insertSelective(LineInfo record);

    LineInfo selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(LineInfo record);

    int updateByPrimaryKey(LineInfo record);
    List<LineInfo> getLineInfoList(LineInfo record);
}