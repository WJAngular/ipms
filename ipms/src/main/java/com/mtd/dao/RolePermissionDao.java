package com.mtd.dao;

import com.mtd.entity.Sys_Permission;
import com.mtd.entity.Sys_Role;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/18.
 */
public interface RolePermissionDao {
    public void save(Object object);

    public void update(Object object);

    public void delete(Object object);

    /**
     * 查询角色拥有的权限id
     * @param roleId
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Integer> findPermissionIds(Integer roleId);

    public List<Sys_Permission> findPermissions(Integer roleId);

    public List<Sys_Role> findRolesWithPermission(Integer permissionId);

    /**
     * 删除角色权限
     * @param roleId
     * @param permissionId
     */
    public void deleteRP(Integer roleId,Integer permissionId);
}
