package com.cxy.dao;


import com.cxy.entity.UserRecord;

public interface UserRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_record
     *
     * @mbggenerated
     */
    int insert(UserRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_record
     *
     * @mbggenerated
     */
    int insertSelective(UserRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_record
     *
     * @mbggenerated
     */
    UserRecord selectByPrimaryKey(Long recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserRecord record);
}