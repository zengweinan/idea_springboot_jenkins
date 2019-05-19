package com.pactera.controller.userInfoController;

import com.pactera.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bo on 2018/12/8.
 */
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private RedisUtils reidsUtils;

    @RequestMapping("/getRedis")
    public void getRedis(){
        //ctrl+alt+v
        boolean abc = reidsUtils.exists("abc");
        if(abc){
            Object abc1 = reidsUtils.get("abc");
            System.out.println(abc1);

        }

    }

}
