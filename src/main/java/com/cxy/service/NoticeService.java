package com.cxy.service;

import com.cxy.entity.LineInfo;

import java.util.List;


public interface NoticeService {
        public void saveByNoticeThread(LineInfo lineInfo);
        public List getMessByUserAndCla(String userid, String classes);
        public void updById(Long id);
}
