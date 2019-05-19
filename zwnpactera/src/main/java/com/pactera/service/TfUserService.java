package com.pactera.service;

import com.pactera.model.TfUser;

import java.util.List;

/**
 * Created by bo on 2018/11/4.
 */
public interface TfUserService {

    TfUser selectUserList();

    TfUser getTFUserByUserCode(String username);

    List<String> selectUserPermissions(String userName);

}
