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
        List<LineInfoAndUserInfo> list= lineInfoMapper.getLineInfoList(lineInfo);
        return list;
    }
}
