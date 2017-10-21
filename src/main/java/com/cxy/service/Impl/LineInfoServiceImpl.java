package com.cxy.service.Impl;

import com.cxy.common.MessageResult;
import com.cxy.common.Pager;
import com.cxy.common.WarningEnum;
import com.cxy.dao.LineInfoMapper;
import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;
import com.cxy.entity.User;
import com.cxy.service.ILineInfoService;
import com.cxy.service.Iidentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lidongpeng on 2017/7/21.
 */
@Service
public class LineInfoServiceImpl implements ILineInfoService {
    @Autowired
    private LineInfoMapper lineInfoMapper;
    @Override
    public MessageResult saveLineInfo(LineInfo lineInfo,HttpServletRequest request) {
        MessageResult messageResult=new MessageResult();
        User user=(User)request.getSession().getAttribute("const_user");
        if (user.getIdentifyStatus()==null||user.getIdentifyStatus()!=1L){
            messageResult.setCode(WarningEnum.need_identified.getCode());
            messageResult.setMessage(WarningEnum.need_identified.getMsg());
            messageResult.setSuccess(false);
            return messageResult;
        }
        lineInfo.setUserId(user.getId().toString());
        lineInfo.setUserMobile(user.getMobile().toString());
        lineInfo.setUserNickname(user.getNickName());
        lineInfo.setStatus(1);
        int size=lineInfoMapper.insertSelective(lineInfo);
        if (size>0){
            messageResult.setCode(WarningEnum.update_success.getCode());
            messageResult.setMessage(WarningEnum.update_success.getMsg());
            messageResult.setSuccess(true);
        }
        return messageResult;
    }

    @Override
    public Pager queryLineInfoList(LineInfo lineInfo,Integer pageIndex,Integer pageSize) {
        lineInfo.setStatus(1);//已发布
        //这里很重要，先查总数，再加下面分页条件
        Integer total=lineInfoMapper.countLineInfo(lineInfo);
        if (pageIndex!=null && pageSize!=null){
            lineInfo.setBegin(pageIndex*pageSize);
            lineInfo.setPageSize(pageSize);
        }
        List<LineInfoAndUserInfo> list= lineInfoMapper.getLineInfoList(lineInfo);
        Pager pager=new Pager();
        pager.setTotal(total.toString());
        pager.setList(list);
        return pager;
    }
    @Override
    public int updateLineInfo(int lid) {
        LineInfo lineInfo=lineInfoMapper.selectByPrimaryKey(lid);
        lineInfo.setStatus(0);//已取消
        int size= lineInfoMapper.updateByPrimaryKey(lineInfo);
        return size;
    }

    @Override
    public LineInfo queryLineInfoById(Integer lid) {
        LineInfo lineInfo=lineInfoMapper.selectByPrimaryKey(lid);
        return lineInfo;
    }
}
