package com.cxy.service;

import com.cxy.common.MessageResult;
import com.cxy.common.Pager;
import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;
import com.cxy.entity.User;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidongpeng on 2017/7/21.
 */
public interface ILineInfoService {
    public MessageResult saveLineInfo(LineInfo lineInfo, HttpServletRequest request);
    public Pager queryLineInfoList(LineInfo lineInfo, Integer start, Integer pageSize,boolean isPublish);
    public int updateLineInfo(int lid);
    LineInfo queryLineInfoById(Integer lid);
    public List<LineInfo>  searchLineinfoListWithCondition(HttpServletRequest request,LineInfo lineInfo);
    public List<LineInfo> querySubLineInfoList(LineInfo lineInfo, Integer start, Integer pageSize);
    public MessageResult updateByLineInfo(LineInfo lineInfo);


    public MessageResult getLineInfoListWithLocation(LineInfo lineInfo,User user);

    public int savelineinfofirsingle(LineInfo lineInfo);
    public Pager queryMyLineInfoList(LineInfo lineInfo, Integer start, Integer pageSize);

}
