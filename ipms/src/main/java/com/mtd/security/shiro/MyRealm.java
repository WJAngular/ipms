package com.mtd.security.shiro;

import javax.annotation.Resource;

import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_UserRole;
import com.mtd.service.UserRoleService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.mtd.entity.Sys_Users;
import com.mtd.service.UserService;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/11/30.
 */
public class MyRealm extends AuthorizingRealm {
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "userRoleService")
    private UserRoleService userRoleService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String)super.getAvailablePrincipal(principals);
        System.out.println("对用户["+currentUsername+"]进行鉴权");
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        if(null!=currentUsername){
            try {
                Sys_Users sysuser = getUserService().findUserByName(currentUsername);
                if (sysuser != null) {
                    List<Sys_UserRole> roles = getUserRoleService().getRoles(sysuser.getId());
                    for (Sys_UserRole role : roles) {
                        System.out.println("已为用户赋予了[" + role.getRole().getName() + "]角色");
                        simpleAuthorInfo.addRole(role.getRole().getName());
                        //添加权限
                        //simpleAuthorInfo.addStringPermission("admin:manage");
                        //System.out.println("已为用户赋予了[admin]角色和[admin:manage]权限");
                    }
                    if(!sysuser.getUsername().equals("yangx")) {
                        simpleAuthorInfo.addStringPermission("sys:wechat");
                        System.out.println("已为用户赋予了[sys:wechat]权限");
                    }
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            return simpleAuthorInfo;
        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
        return null;
    }


    /**
     * 验证当前登录的Subject
     * @see  经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        Sys_Users sysuser = getUserService().findUserByName(token.getUsername());
        if (sysuser != null) {
          AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(sysuser.getUsername(), sysuser.getPassword(), getName());
          this.setSession("sysuser", sysuser);
          return authcInfo;
      }else{
          return null;
      }
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }
}