package com.pactera.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pactera.common.WeixinCommon;
import com.pactera.model.TfUser;
import com.pactera.service.TfUserService;
import com.pactera.service.impl.SendMqMessageServiceImpl;
import com.pactera.util.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengweinan on 2018/11/5.
 * ctrl+alt+v不全返回值
 */

@Controller
public class LoginController {
    @Autowired
    private TfUserService tfUserService;
    @Autowired
    private RedisUtils reidsUtils;
    @Autowired
    private SendMqMessageServiceImpl mqListenerService;

    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {
        return "login";
    }



    @RequestMapping(value = "getCodeByWebAuthorize", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCodeByWebAuthorize(String code,String state) {
        System.out.print("22");
        try {
            System.out.println(code);
            //根据code获取网页授权
            String webAccessTokenUrl = WeixinCommon.getWebAccessTokenUrl(code);
            JSONObject jsonObject = JSON.parseObject(webAccessTokenUrl);
            //授权后的accessToken
            String accessToken = (String) jsonObject.get("access_token");
            String openId = (String) jsonObject.get("openid");
            //根据上面的accessToken,openId拉取用户信息(放到session)
            String webUserInfoUrl = WeixinCommon.getWebUserInfoUrl(accessToken, openId);
            System.out.print("网页用户信息:" + webUserInfoUrl);
        } catch (Exception e) {
        }
        return "login";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("/loginInfo")
    public String LoginInfo(HttpServletRequest request) {
        try {//alt+shift+z
            //shiro的会话管理
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Session session = SecurityUtils.getSubject().getSession();//要在web.xml配置拦截器,以及配置ShiroConfig(主要)
            //使用了jedis密码登录(在RedisConfig中获取jedis是加了密码)
            reidsUtils.set("abc", "成功了对对对我");
            if (!StringUtils.isEmpty(username)) { //查询是否为空
                //根据id去查询当前用户
                System.out.println("有报错");
                TfUser tfuser = getTFUserByUserCode(username);
                if (tfuser == null) {//返回登录页面
                    return "redirect:/login";
                } else {
                    test("1");
                    //放到shiro中的session
                    UsernamePasswordToken token = new UsernamePasswordToken(username, "");
                    //为了用户的登录的认证,和授权.调用ShiroRealm中的方法
                    SecurityUtils.getSubject().login(token);
                }
                //存到session
                session.setAttribute("currentUser", tfuser);
            }
            TfUser user = (TfUser) session.getAttribute("currentUser");
            System.out.println(user.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login";
        }
        return "/index";
    }

    //根据用户去查询是否在
    private TfUser getTFUserByUserCode(String username) {
        boolean abc = reidsUtils.exists("abc");
        if (abc) {
            Object abc1 = reidsUtils.get("abc");
            System.out.println(abc1);
        }
        return tfUserService.getTFUserByUserCode(username);
    }

    @RequiresPermissions({"delivery:complete"})
    @RequestMapping("/aa")
    private void requirePermission() {
        System.out.println(222);
    }

    private void test(String name) {
        System.out.println("22222ddddd");
    }

    //@Scheduled(cron = "0 */2 17 * * ?")
    @RequestMapping("/mqMessage")
    public void sendMessage() {
        try {
            mqListenerService.sendRabblitMqmssage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
