package com.cxy.service.Impl;

import com.cxy.common.MessageResult;
import com.cxy.common.Pager;
import com.cxy.common.UserTools;
import com.cxy.common.WarningEnum;
import com.cxy.dao.LineInfoMapper;
import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;
import com.cxy.entity.User;
import com.cxy.entity.UserRecord;
import com.cxy.service.ILineInfoService;
import com.cxy.service.Iidentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lidongpeng on 2017/7/21.
 */
@Service
public class LineInfoServiceImpl implements ILineInfoService {
    @Autowired
    private LineInfoMapper lineInfoMapper;
    private ExecutorService executor;
    public LineInfoServiceImpl(){
        executor= Executors.newSingleThreadExecutor();
    }
    @Override
    public MessageResult saveLineInfo(LineInfo lineInfo,HttpServletRequest request) {
        MessageResult messageResult=new MessageResult();
        User user=(User)request.getSession().getAttribute("const_user");
        //鉴于刚开始用户少，先屏蔽掉实名认证后才可以发布
 /*       if (user.getIdentifyStatus()==null||user.getIdentifyStatus()!=1L){
            messageResult.setCode(WarningEnum.need_identified.getCode());
            messageResult.setMessage(WarningEnum.need_identified.getMsg());
            messageResult.setSuccess(false);
            return messageResult;
        }*/
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
    public MessageResult updateByLineInfo(LineInfo lineInfo){
        MessageResult messageResult=new MessageResult();
        int size = lineInfoMapper.updateByPrimaryKeySelective(lineInfo);
        if(size>0){
            messageResult.setCode(WarningEnum.update_success.getCode());
            messageResult.setMessage(WarningEnum.update_success.getMsg());
            messageResult.setSuccess(true);
        }else{
            messageResult.setCode(WarningEnum.update_failed.getCode());
            messageResult.setMessage(WarningEnum.update_failed.getMsg());
            messageResult.setSuccess(false);
        }
        return messageResult;
    }

    @Override
    public MessageResult getLineInfoListWithLocation(LineInfo lineInfo,User user) {
        MessageResult<List<LineInfo>> messageResult=new MessageResult();
        //如果用户已经登录，将用户的查询信息发布出去,这里多线程情况下有些不稳定，先走单线程
/*        executor.execute(new Runnable() {
            @Override
            public void run() {*/
        int linetype=lineInfo.getType();
                if (user!=null){
                    lineInfo.setStatus(1);
                    lineInfo.setUserMobile(user.getMobile().toString());
                    lineInfo.setUserId(user.getId().toString());
                    lineInfo.setUserNickname(user.getNickName());
                    lineInfo.setType(1^lineInfo.getType());
                    //出发时间默认在当前时间加5个小时
                    lineInfo.setStartTime(UserTools.addDateMinut(UserTools.getCurrentTime(),5));
                    lineInfoMapper.insertSelective(lineInfo);
                }
/*            }
        });*/
        lineInfo.setStart("");
        lineInfo.setEnd("");
        lineInfo.setStartTime("");
        lineInfo.setType(linetype);
        List<LineInfo> list=lineInfoMapper.getLineInfoListForLocation(lineInfo);
        if (list!=null&&list.size()>0){
            messageResult.setBuessObj(list);
            messageResult.setSuccess(true);
            return messageResult;
        }else{
            messageResult.setSuccess(false);
        }

        return messageResult;
    }

    @Override
    public int savelineinfofirsingle(LineInfo lineInfo) {
        return lineInfoMapper.insertSelective(lineInfo);
    }

    /**
     *
     * @param lineInfo
     * @param pageIndex
     * @param pageSize
     * @param isPublish  ---用于区分我的发布和首页找车信息
     * @return
     */
    @Override
    public Pager queryLineInfoList(LineInfo lineInfo,Integer pageIndex,Integer pageSize,boolean isPublish) {
        lineInfo.setStatus(1);//已发布
        LineInfo cancerInfo = new LineInfo();//取消发布的用于再次编辑
        cancerInfo.setUserId(lineInfo.getUserId());
        cancerInfo.setStatus(0);
        //这里很重要，先查总数，再加下面分页条件
        Integer total=lineInfoMapper.countLineInfo(lineInfo);
        if(isPublish){
            total = total + lineInfoMapper.countLineInfo(cancerInfo);
        }
        if (pageIndex!=null && pageSize!=null){
            lineInfo.setBegin(pageIndex*pageSize);
            lineInfo.setPageSize(pageSize);
        }
        List<LineInfoAndUserInfo> list= lineInfoMapper.getLineInfoList(lineInfo);
        if(isPublish) {
            list.addAll(lineInfoMapper.getLineInfoList(cancerInfo));
        }
        //加上取消发布的信息
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

    public String getMsgByUser(User user){
        String msg = "";
        Integer cnt =  lineInfoMapper.getLineInfoSubCount(user.getId());
        if(cnt!=null&&cnt>0){
            msg = "已预约<a class=\"menu-child\" href=\"/v1/mySubLineInfo\">【"+cnt+"】</a>";
        }
        return msg;
    }

    @Override
    public Pager querySubLineInfoList(LineInfo lineInfo,Integer pageIndex,Integer pageSize) {
        lineInfo.setStatus(1);//已发布
        //这里很重要，先查总数，再加下面分页条件
        Integer total=lineInfoMapper.countSubLineInfo(lineInfo);
        if (pageIndex!=null && pageSize!=null){
            lineInfo.setBegin(pageIndex*pageSize);
            lineInfo.setPageSize(pageSize);
        }
        List<LineInfoAndUserInfo> list= lineInfoMapper.getSubLineInfoList(lineInfo);
        Pager pager=new Pager();
        pager.setTotal(total.toString());
        pager.setList(list);
        return pager;
    }

}
