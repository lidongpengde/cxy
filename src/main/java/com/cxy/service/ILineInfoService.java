package com.cxy.service;

import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;

import java.util.List;

/**
 * Created by lidongpeng on 2017/7/21.
 */
public interface ILineInfoService {
    public int saveLineInfo(LineInfo lineInfo);
    public List<LineInfoAndUserInfo> queryLineInfoList(LineInfo lineInfo);
    public int updateLineInfo(int lid);
}
