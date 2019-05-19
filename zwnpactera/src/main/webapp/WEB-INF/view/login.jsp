<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录系统</title>
</head>
<script type="javascript">
   $(function () {

       window.location.href ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2a1ccc1689bf5c09&redirect_uri=http://zwn.nat300.top/getCodeByWebAuthorize&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
   })

</script>
<body>
<form action="${ctx}/loginInfo" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></input></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password"></input></td>
        </tr>
        <tr>
            <td>提交</td>
            <td><input type="submit" value="不要点我"></input></td>
        </tr>
    </table>
</form>

</body>
</html>