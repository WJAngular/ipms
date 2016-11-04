package com.mtd.service;

import com.mtd.dao.RolePermissionDao;
import com.mtd.entity.Sys_Permission;
import com.mtd.entity.Sys_Role;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/20.
 */
public interface RolePermissionService {
    public RolePermissionDao getEntityDao();

    /**
     * 获取角色权限id集合
     * @param roleId
     * @return List
     */
    public List<Integer> getPermissionIds(Integer roleId);

    public List<Sys_Permission> getPermissions(Integer roleId);

    public List<Sys_Role> findRolesWithPermission(Integer permissionId);

    /**
     * 修改角色权限
     * @param id
     * @param oldList
     * @param newList
     */
    @Transactional(readOnly = false)
    public void updateRolePermission(Integer id,List<Integer> oldList,List<String> newList);

    /**
     * 清空该角色用户的权限缓存
     */
    public void clearUserPermCache(PrincipalCollection pc);
}
