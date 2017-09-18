package com.cxy.service;

import com.cxy.common.MessageResult;
import com.cxy.entity.LineInfo;
import com.cxy.entity.Subscribe;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidongpeng on 2017/9/18.
 */
public interface IsubscribeService {
    public MessageResult addSubscibe(HttpServletRequest request, Subscribe subscribe);
    public List<Subscribe> querySubscibeList(LineInfo lineInfo);
}
