package com.cxy.dao;



import com.cxy.entity.TCxyQuestions;

import java.util.List;

public interface TCxyQuestionsMapper {


    int insert(TCxyQuestions record);

    int insertSelective(TCxyQuestions record);

}