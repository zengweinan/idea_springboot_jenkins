<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录系统</title>
</head>
<body>
<form action="" method="post">
    <table>
        <tr>
            <td>测试：</td>
            <td><input type="text" name="username"></input></td>
        </tr>
        <tr>
            <td>测试：</td>
            <td><input type="text" name="password"></input></td>
        </tr>
        <tr>delivery:infodispcth
            <td></td>
            <td>
                <shiro:hasPermission name="delivery:infodispcth">
                <input type="text" onclick="test()" value="不要点我"></input>
                </shiro:hasPermission>
            </td>
        </tr>
    </table>
</form>

</body>
</html>