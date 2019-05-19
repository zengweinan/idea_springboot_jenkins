/**
 *
 */
package com.pactera.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pactera.common.WeixinCommon;
import com.pactera.util.SecurityUtil;
import com.pactera.util.XmlMessageEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;

/**
 * @author zwn
 * @version 1.0
 * @Description: 微信相关的
 * @date 2019年3月18日
 */
@Controller
@RequestMapping("weixin")
public class WeiXinController {

    /**
     * 验证消息来源
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value = "/getVerification", method = {RequestMethod.GET})
    @ResponseBody
    public String getVerification(String signature, String timestamp, String nonce, String echostr) {
        // 1:将token、timestamp、nonce三个参数进行字典序排序
        String[] str = new String[]{WeixinCommon.TOKEN_WEIXIN, timestamp, nonce};
        // Arrays.sort 可以对一些基本数据类型数组排序
        Arrays.sort(str);
        StringBuffer buffer = new StringBuffer();
        for (String s : str) {
            buffer.append(s.toString());
        }
        // 2:将三个参数字符串拼接成一个字符串进行sha1加密
        //StringBuffer stringBuffer = buffer.append(LifeConstants.TOKEN_WEIXIN).append(timestamp).append(nonce);
        String md5 = SecurityUtil.SHA1(buffer.toString());
        System.out.print(md5);
        // 3:开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (md5.equals(signature)) {
            return echostr;
        }
        return null;
    }

    /**
     * 接收消息/首次关注/取消关注
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getVerification", method = {RequestMethod.POST},produces="application/xml")
    @ResponseBody
    public XmlMessageEntity handleMessage(@RequestBody XmlMessageEntity entity) {
        //首次关注为msgType:event;
        //Event :subscribe//事件
        System.out.print("entity:"+entity);
        XmlMessageEntity newEntity = new XmlMessageEntity();
        //消息id，64位整型
       // newEntity.setMsgId(entity.getMsgId());
        //回复信息
        //发送方:开发发送 发送方帐号（一个OpenID）
        newEntity.setFromUserName(entity.getToUserName());
        //接收方:用户接收
        newEntity.setToUserName(entity.getFromUserName());
        newEntity.setCreateTime(new Date().getTime());
        //文本消息内容
        //newEntity.setContent(entity.getContent());
        //消息类型，文本为text
        newEntity.setMsgType("text");
        //如果是是首次关注
        if("event".equals(entity.getMsgType())){
            //如果是关注事件
            if("subscribe".equals(entity.getEvent())){
                //可以保存用户信息
                try {
                    //全局的accessToken
                    String accessToken = WeixinCommon.getAccessToken();
                    String userInfo = WeixinCommon.getUserInfo(entity.getFromUserName());
                    System.out.print("userInfo:"+userInfo);
                    JSONObject jsonObject = JSON.parseObject(userInfo);
                }catch (Exception e){

                }
                //回复内容;
                newEntity.setContent("欢迎关注,客关等你好久了,叫你关注你还真关注");
            }else if("unsubscribe".equals(entity.getEvent())){
                //取消关注;更改用户之类的

            }else{
                newEntity.setContent("click事件");
            }
        }
        if("text".equals(entity.getMsgType())){
            newEntity.setContent("没有你想要的数据,不要再发了");
        }

        return newEntity;
    }

}
