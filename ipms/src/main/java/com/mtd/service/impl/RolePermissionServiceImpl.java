package com.mtd.service.impl;

import com.mtd.dao.PermissionDao;
import com.mtd.dao.RolePermissionDao;
import com.mtd.entity.Sys_Permission;
import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_RolePermission;
import com.mtd.security.shiro.MyRealm;
import com.mtd.service.RolePermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/20.
 */
public class RolePermissionServiceImpl implements RolePermissionService{

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public RolePermissionDao getEntityDao() {
        return rolePermissionDao;
    }

    public void setRolePermissionDao(RolePermissionDao dao) {
        this.rolePermissionDao = dao;
    }

    /**
     * 获取角色权限id集合
     * @param roleId
     * @return List
     */
    public List<Integer> getPermissionIds(Integer roleId){
        return rolePermissionDao.findPermissionIds(roleId);
    }

    public List<Sys_Permission> getPermissions(Integer roleId){ return rolePermissionDao.findPermissions(roleId); }

    public List<Sys_Role> findRolesWithPermission(Integer permissionId){ return rolePermissionDao.findRolesWithPermission(permissionId); }

    /**
     * 修改角色权限
     * @param id
     * @param oldList
     * @param newList
     */
    @Transactional(readOnly = false)
    public void updateRolePermission(Integer id,List<Integer> oldList,List<String> newList){
        //是否删除
        for(int i=0,j=oldList.size();i<j;i++){
            if(!newList.contains(oldList.get(i))){
                rolePermissionDao.deleteRP(id,oldList.get(i));
            }
        }

        //是否添加
        for(int i=0,j=newList.size();i<j;i++){
            if(!oldList.contains(newList.get(i))){
                rolePermissionDao.save(getRolePermission(id,Integer.parseInt(newList.get(i)) ));
            }
        }
    }

    /**
     * 清空该角色用户的权限缓存
     */
    public void clearUserPermCache(PrincipalCollection pc){
        RealmSecurityManager securityManager =  (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyRealm userRealm = (MyRealm) securityManager.getRealms().iterator().next();
        userRealm.clearCachedAuthorizationInfo(pc);
    }

    /**
     * 构造角色权限对象
     * @param roleId
     * @param permissionId
     * @return RolePermission
     */
    private Sys_RolePermission getRolePermission(Integer roleId,Integer permissionId){
        Sys_RolePermission rp=new Sys_RolePermission();
        rp.setRole(new Sys_Role(roleId));
        rp.setPermission(new Sys_Permission(permissionId));
        return rp;
    }

}
