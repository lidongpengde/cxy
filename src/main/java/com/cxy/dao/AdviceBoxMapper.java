package com.cxy.dao;


import com.cxy.entity.AdviceBox;

public interface AdviceBoxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long adviceBoxId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    int insert(AdviceBox record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    int insertSelective(AdviceBox record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    AdviceBox selectByPrimaryKey(Long adviceBoxId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AdviceBox record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice_box
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AdviceBox record);
}