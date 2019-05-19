package com.pactera.service.impl;

import com.pactera.mapper.TfUserMapper;
import com.pactera.model.TfUser;
import com.pactera.service.TfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bo on 2018/11/4.
 */
@Service
public class TfUserServiceImpl implements TfUserService {

    @Autowired//重写方法,alt+inster
    private TfUserMapper tfUserMapper;
    @Override
    public TfUser selectUserList() {
        System.out.println(33333);
        TfUser tfUser = new TfUser();
        tfUser.setId(62756);
        tfUser.setUserid("        tfUser.setUserid(aishujun);\n");
        return tfUserMapper.selectUserById(62756);
    }

    @Override
    public TfUser getTFUserByUserCode(String username) {
        TfUser tfUser = tfUserMapper.selectUserInfoByUserName(username);
        return tfUser;
    }

    @Override
    public List<String> selectUserPermissions(String userName) {
        return tfUserMapper.selectUserPermissions(userName,"LIFE");
    }




}
