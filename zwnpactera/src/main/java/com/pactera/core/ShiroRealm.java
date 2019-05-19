//package com.pactera.core;
//
//import com.pactera.model.TfUser;
//import com.pactera.service.TfUserService;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
///**
// * Created by bo on 2018/11/7.
// * 2018-11-17总结,执行 SecurityUtils.getSubject().login(token);的时候执行认证方法doGetAuthenticationInfo
// * 当用户有权限的时候,页面要有 <shiro:hasPermission name="delivery:infodispcth">标签才执行授权方法,doGetAuthorizationInfo
// */
//public class ShiroRealm extends AuthorizingRealm{
//
//    @Autowired
//    private TfUserService tfUserService;
//    /**
//     * 授权回调函数(将用户的角色和权限添加到认证器中).
//     * @param principalCollection
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //获取当前用户
//        String userName = (String) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        if (StringUtils.isEmpty(userName)) {
//            //查询当前用户所拥有的权限
//            List<String> permissions = this.tfUserService.selectUserPermissions(userName);
//            for (String permission : permissions) {
//                if (StringUtils.isEmpty(permission)) {
//                    //授予权限
//                    info.addStringPermission(permission);
//                }
//            }
//            return info;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * 权限认证回调函数,登录时调用.(先执行)
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String userName = (String) authenticationToken.getPrincipal();
//        TfUser user = this.tfUserService.getTFUserByUserCode(userName);
//        if (user != null) {
//            return new SimpleAuthenticationInfo(user.getUserid(), "", getName());
//        } else {
//            return null;
//        }
//    }
//}
