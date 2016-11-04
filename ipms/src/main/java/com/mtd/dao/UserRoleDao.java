package com.mtd.dao;

/**
 * Created by Wooce Yang on 2015/12/18.
 */
import java.util.List;

import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_UserRole;
import org.springframework.stereotype.Repository;


/**
 * 用户角色DAO
 * @date 2015年1月13日
 */
@Repository
public interface UserRoleDao{
    public void save(Object object);

    public void update(Object object);

    public void delete(Object object);

    /**
     * 删除用户角色
     * @param userId
     * @param roleId
     */
    public void deleteUR(Integer userId,Integer roleId);

    /**
     * 查询用户拥有的角色id集合
     * @param userId
     * @return 结果集合
     */
    @SuppressWarnings("unchecked")
    public List<Integer> findRoleIds(Integer userId);

    public List<Sys_UserRole> findRoles(Integer userId);

    public boolean hasRole(Integer roleId);
}
