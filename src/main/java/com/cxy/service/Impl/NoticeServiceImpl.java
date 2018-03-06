package com.cxy.service.Impl;

import com.cxy.common.NoticeThread;
import com.cxy.dao.LineInfoMapper;
import com.cxy.dao.NoticeMapper;
import com.cxy.entity.LineInfo;
import com.cxy.entity.Notice;
import com.cxy.entity.Subscribe;
import com.cxy.service.ILineInfoService;
import com.cxy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    public  void saveByNoticeThread(LineInfo lineInfo){
        Notice notice = new Notice();
        notice.setIsread(0);
        notice.setClasses(LineInfo.class.getSimpleName());
        notice.setUserid(lineInfo.getUserId());
        notice.setBusinessid(lineInfo.getLid());
        notice.setMessages("您的【"+lineInfo.getStart()+"】-->【"+lineInfo.getEnd()+"】发布已被预约，请注意查看");
        new NoticeThread(notice);
    }
    public List getMessByUserAndCla(String userid, String classes){
        return noticeMapper.getMessByUserAndCla(userid,classes);
    }
    public  void updById(Long id){
        noticeMapper.updById(id);
    }
}
