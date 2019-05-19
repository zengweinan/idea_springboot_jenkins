package com.pactera.mapper;

import com.pactera.model.TfUser;
import com.pactera.model.TfUserKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TfUserMapper {
    int deleteByPrimaryKey(TfUserKey key);

    int insert(TfUser record);

    int insertSelective(TfUser record);

    TfUser selectByPrimaryKey(TfUserKey key);

    int updateByPrimaryKeySelective(TfUser record);

    int updateByPrimaryKey(TfUser record);

    TfUser selectUserById(@Param("id") int id);

    TfUser selectUserInfoByUserName(@Param("userName")String userName);

    List<String> selectUserPermissions(@Param("userName")String userName, @Param("appId")String appId);
}