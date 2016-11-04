package com.mtd.common.utils;

import com.mtd.entity.Sys_Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Created by Wooce Yang on 2015/12/21.
 */
public class UserUtil {
    /**
     * ��ȡ��ǰ�û�����
     * @return user
     */
    public static Sys_Users getCurrentUser(){
        Session session  = SecurityUtils.getSubject().getSession();
        if(null!=session){
            return (Sys_Users) session.getAttribute("sysuser");
        }else{
            return null;
        }

    }
}
