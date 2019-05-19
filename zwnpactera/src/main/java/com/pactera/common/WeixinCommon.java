package com.pactera.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pactera.util.HttpUtil;

import java.util.Date;

/**
 * Created by zengweinan on 2019/3/23.
 * 微信工具类
 */
public class WeixinCommon {

    /**
     * toke:验证的token
     */
    public static String TOKEN_WEIXIN = "weinanzeng";

    /**
     * appID:开发者账号
     */
    public static String APPID = "wx2a1ccc1689bf5c09";

    /**
     * appsecret:开发者密码
     */
    public static String APPSECRET = "a7c5ef1ccab5d7be54c62c92e7180c3d";
    /**
     * 获取access_token中控服务器地址url
     */
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取用户基本信息url需要关注
     */
    public static String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /**
     * 自定义菜单url
     */
    public static String CUSTOM_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 删除自定义菜单
     */
    public static String DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    /**
     * 获取网页授权code地址(用工具直接访问,要设计网页授权区域,把域名设置上或者主动请求)
     */
    public static String WEB_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    /**
     * 通过code换取网页授权access_token
     */
    public static String WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 获取用户的基本信息(不用关注的)网页版的
     */
    public static String WEB_USER_INFO_URL = " https://api.weixin.qq" + ".com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";


    /**
     * 设置所属行业
     */
    public static String API_SET_INDUSTRY = "https://api.weixin.qq  .com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";

    /**
     * 获取设置的行业信息
     */
    public static String API_GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";

    /**
     * 获取模板列表
     */

    public static String GET_ALL_PRIVATE_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";

    /**
     * 发送模板信息
     */
    public static String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq" + ".com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";


    /**
     * 上传图文消息素材(要先上传图片,然后拿到图片的thumb_media_id,然后再根据上传图文消息返回的media_id进行群发)
     */
    public static String WX_UPLOADNEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";

    /**
     * 根据OpenID列表群发(这个是预览地址,测试用的)
     *
     */
    public static String MASS_SEND_OPENID ="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";

    /**
     * 定义一个access_token放到内存中
     */

    public static String accessToken;

    /**
     * accessToken的失效时间
     */
    public static Long expiresInTime = 0L;

    /**
     *根据OpenID列表群发
     */
    public static String getMassSendOpenid(String param){
        String result = MASS_SEND_OPENID.replace("ACCESS_TOKEN", getAccessToken());
        String post = HttpUtil.post(result, param);
        return post;
    }


    /**
     * 上传图文消息素材
     * @param param
     * @return
     */
    public static String getWxUploadnews(String param){
        String result = WX_UPLOADNEWS.replace("ACCESS_TOKEN", getAccessToken());
        String post = HttpUtil.post(result, param);
        return post;
    }

    /**
     * 获取所属行业
     */
    public static String getApiGetIndustry() {
        String result = API_GET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken());
        return HttpUtil.get(result);
    }

    /**
     * 设置所属行业
     *
     * @return
     */
    public static void getApiSetIndustry(String param) {
        String result = API_SET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken());
        HttpUtil.post(result, param);
        System.out.print(result);

    }

    /**
     * 获取模板列表
     */
    public static String getGetAllPrivateTemplate() {
        String result = GET_ALL_PRIVATE_TEMPLATE.replace("ACCESS_TOKEN", getAccessToken());
        return HttpUtil.get(result);
    }

    /**
     * 发送模板消息
     */
    public static String sendTemplate(String param) {
        String result = SEND_TEMPLATE_MESSAGE.replace("ACCESS_TOKEN", getAccessToken());
        String post = HttpUtil.post(result, param);
        return post;
    }

    /**
     * 在差不多到两个小时的时候刷新一下access_token,
     * 需定时刷新，重复获取将导致上次获取的access_token失效。
     *
     * @return
     */
    public static String getAccessToken() {
        //如果accessToken为空或者已经失效,去刷新,重新获取(提前8秒获取)
        if (new Date().getTime() >= expiresInTime) {
            //替换字符
            String result = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
            //使用http请求(返回access_token和expires_in)json
            String accessTokenAndExpiresIn = HttpUtil.get(result);
            //使用json取出来
            JSONObject jsonObject = JSON.parseObject(accessTokenAndExpiresIn);
            //返回access_token
            accessToken = (String) jsonObject.get("access_token");
            Integer expiresIn = (Integer) jsonObject.get("expires_in");
            //失效时间 = 当前时间(毫秒)+ 7200
            expiresInTime = new Date().getTime() + ((expiresIn - 100) * 1000);

        }
        //返回时间的
        return accessToken;
    }

    /**
     * 获取用户基本信息
     */
    public static String getUserInfo(String openId) {
        //替换字符
        String result = USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        //使用http请求(返回access_token和expires_in)json
        String accessTokenAndExpiresIn = HttpUtil.get(result);
        return accessTokenAndExpiresIn;
    }

    /**
     * 自定义菜单
     */
    public static void createMenu(String menu) {
        //替换字符
        String result = CUSTOM_MENU.replace("ACCESS_TOKEN", getAccessToken());
        //使用http请求(返回access_token和expires_in)json
        String accessTokenAndExpiresIn = HttpUtil.post(result, menu);
        System.out.print(accessTokenAndExpiresIn);
    }

    /**
     * 删除自定义菜单
     */
    public static void deteleMenu() {
        String result = DELETE_MENU.replace("ACCESS_TOKEN", getAccessToken());
        System.out.print(HttpUtil.post(result, result));
    }

    /**
     * 获取code
     */
    public static String getCode() {
        String result = WEB_AUTHORIZE_URL.replace("APPID", APPID).replace("REDIRECT_URI", "http://zwn.nat300.top/getCodeByWebAuthorize");
        System.out.print("result:----" + result);
        return HttpUtil.get(result);
    }

    /**
     * 通过code换取网页授权access_token
     *
     * @param
     */
    public static String getWebAccessTokenUrl(String code) {
        String result = WEB_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
        String webAccessTokenUrl = HttpUtil.get(result);
        return webAccessTokenUrl;
    }

    /**
     * 通过oauth2后的access_token,拉取用户信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static String getWebUserInfoUrl(String accessToken, String openId) {
        String result = WEB_USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        String webUserInfoUrl = HttpUtil.get(result);
        return webUserInfoUrl;
    }


    public static void main(String[] args) {

        //System.out.print(getAccessToken());
        //群发(预览的)
        String sendMessageOpen= getMassSendOpenid(sendMessageOpenid);
        //发送图文
       // String upload =getWxUploadnews(uploadNews);
        //JSONObject jsonObject = JSON.parseObject(upload);
        //media_id:MO1KBox5jFRqY13fCTxRFk5hC5lyvjjGpckn3jB7RJ58_1U0QnHx-oyYF6zwjUvx
        // String mediaId = (String)jsonObject.get("media_id");
        //System.out.print(mediaId);
        System.out.print("成功群发啦"+sendMessageOpen);
        //mediaId:AjffBi7nybvBNTBhUWyequMguIVtHbTpz9Y_oe55AQWVVgn8NBRVnp9EYAeMdSkP
        //发送模板
        //String s = sendTemplate(template);
        //System.out.print(s);
        //String getAllPrivateTemplate = getGetAllPrivateTemplate();
        //JSONObject jsonObject = JSON.parseObject(getAllPrivateTemplate);
        // System.out.print(jsonObject);
        // System.out.print( getApiGetIndustry());
        //创建自定义菜单
        // deteleMenu();
        // createMenu(MENU);

    }




    //自定义菜单
    public static String MENU = " {\n" + "     \"button\":[\n" + "     {    \n" + "          \"type\":\"click\",\n" + "          \"name\":\"功啦88曲daadd\",\n" + "          \"key\":\"V1001_TODAY_MUSIC\"\n" + "      },\n" + "      {\n" + "           \"name\":\"aa菜单\",\n" + "           \"sub_button\":[\n" + "           {    \n" + "               \"type\":\"view\",\n" + "               \"name\":\"aa搜索\",\n" + "               \"url\":\"http://www.soso.com/\"\n" + "            },\n" + "            {\n" + "                 \"type\":\"miniprogram\",\n" + "                 \"name\":\"wxa\",\n" + "                 \"url\":\"http://mp.weixin.qq.com\",\n" + "                 \"appid\":\"wx286b93c14bbf93aa\",\n" + "                 \"pagepath\":\"pages/lunar/index\"\n" + "             },\n" + "            {\n" + "               \"type\":\"click\",\n" + "               \"name\":\"赞一下我们\",\n" + "               \"key\":\"V1001_GOOD\"\n" + "            }]\n" + "       }]\n" + " }";

    //获取所属行业
    public static String INDUSTRY = "{\n" + "    \"industry_id1\":\"1\",\n" + "    \"industry_id2\":\"4\"\n" + "}";

    //发送模板消息
    public static String template = " {\n" + "           \"touser\":\"oF-9T1iJwvpqQ0gsRFf4pZsLdFBw\",\n" + "           \"template_id\":\"Un5mQsZxkFGzk7DRCNbwW6KeDKLCH57zrSRXhsZBJFM\",\n" + "           \"url\":\"http://weixin.qq.com/download\",       \n" + "           \"data\":{\n" + "                   \"first\": {\n" + "                       \"value\":\"恭喜你购买成功！\",\n" + "                       \"color\":\"#173177\"\n" + "                   },\n" + "                   \"keyword1\":{\n" + "                       \"value\":\"巧克力\",\n" + "                       \"color\":\"#173177\"\n" + "                   },\n" + "                   \"keyword2\": {\n" + "                       \"value\":\"39.8元\",\n" + "                       \"color\":\"#173177\"\n" + "                   },\n" + "                   \"keyword3\": {\n" + "                       \"value\":\"2014年9月22日\",\n" + "                       \"color\":\"#173177\"\n" + "                   },\n" + "                   \"remark\":{\n" + "                       \"value\":\"欢迎再次购买！\",\n" + "                       \"color\":\"#173177\"\n" + "                   }\n" + "           }\n" + "       }";

    //上传图文消息素材json
    public static String uploadNews = "{\n" + "   \"articles\": [     {\n" + "                        \"thumb_media_id\":\"lMlrvTfCMLV3S-Qp2oSDANYh_6_DeQAIbF9NJbXgY5ABd1cBPpxjKf8_I3rXQLD8\",\n" + "                        \"author\":\"文思海辉\",        \n" + "                        \"title\":\"公众测试\",         \n" + "                        \"content_source_url\":\"www.baidu.com\",        \n" + "                        \"content\":\"就想问问你成功发送没有\",         \n" + "                        \"digest\":\"那个\",\n" + "                        \"show_cover_pic\":1,\n" + "                        \"need_open_comment\":1,\n" + "                        \"only_fans_can_comment\":1\n" + "                        },     \n" + "                        \n" + "   ]\n" + "}";


    //根据openid进行群发
    public static String sendMessageOpenid="{\n" + "   \"touser\":\"oF-9T1pzSpeYb8T_GZABcipTNme0\",\n" + "  \"mpnews\":{\n" + "      \"media_id\":\"W--S2Y3PWz6nyamoNo2HzqoO5LaMWCxXDfHhi4oxjdFGcgEQd_9EKen4hlfxRN7e\"\n" + "   },\n" + "    \"msgtype\":\"mpnews\"，\n" + "    \"send_ignore_reprint\":0\n" + "}";

};
