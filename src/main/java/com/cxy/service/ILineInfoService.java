package com.cxy.service;

import com.cxy.entity.LineInfo;

import java.util.List;

/**
 * Created by lidongpeng on 2017/7/21.
 */
public interface ILineInfoService {
    public int saveLineInfo(LineInfo lineInfo);
    public List<LineInfo> queryLineInfoList(LineInfo lineInfo);
}
