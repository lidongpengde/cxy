package com.cxy.service;

import com.cxy.common.MessageResult;
import com.cxy.common.Pager;
import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidongpeng on 2017/7/21.
 */
public interface ILineInfoService {
    public MessageResult saveLineInfo(LineInfo lineInfo, HttpServletRequest request);
    public Pager queryLineInfoList(LineInfo lineInfo, Integer start, Integer pageSize);
    public int updateLineInfo(int lid);
}
