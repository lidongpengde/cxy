package com.cxy.service.Impl;

import com.cxy.dao.LineInfoMapper;
import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;
import com.cxy.service.ILineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lidongpeng on 2017/7/21.
 */
@Service
public class LineInfoServiceImpl implements ILineInfoService {
    @Autowired
    LineInfoMapper lineInfoMapper;
    @Override
    public int saveLineInfo(LineInfo lineInfo) {
        int size=lineInfoMapper.insert(lineInfo);
        return size;
    }

    @Override
    public List<LineInfoAndUserInfo> queryLineInfoList(LineInfo lineInfo) {
        lineInfo.setStatus(1);//已发布
        List<LineInfoAndUserInfo> list= lineInfoMapper.getLineInfoList(lineInfo);
        return list;
    }
    @Override
    public int updateLineInfo(int lid) {
        LineInfo lineInfo=lineInfoMapper.selectByPrimaryKey(lid);
        lineInfo.setStatus(0);//已取消
        int size= lineInfoMapper.updateByPrimaryKey(lineInfo);
        return size;
    }
}
