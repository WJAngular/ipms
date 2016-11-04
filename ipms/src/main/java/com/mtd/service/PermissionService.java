package com.mtd.service;

/**
 * Created by Wooce Yang on 2015/12/19.
 */
import java.util.List;

import com.mtd.dao.PermissionDao;
import com.mtd.entity.Sys_Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限service
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly=true)
public interface PermissionService{

    public PermissionDao getEntityDao();

    public Sys_Permission get(Integer id);

    public void save(Object object);

    public void update(Object object);

    public void delete(Object object);

    public List<Sys_Permission> getAll();

    /**
     * 添加菜单基础操作
     * @param pid 菜单id
     * @param pClassName 菜单权限标识名
     */
    @Transactional(readOnly = false)
    public void addBaseOpe(Integer pid,String pClassName);

    /**
     * 获取角色拥有的权限集合
     * @param userId
     * @return 结果集合
     */
    public List<Sys_Permission> getPermissions(Integer userId);

    /**
     * 获取角色拥有的菜单
     * @param userId
     * @return 菜单集合
     */
    public List<Sys_Permission> getMenus(Integer userId);

    /**
     * 获取所有菜单
     * @return 菜单集合
     */
    public List<Sys_Permission> getMenus();

    public List<Sys_Permission> getMenus(int pagesize, int offset);

    /**
     * 获取菜单下的操作
     * @param pid
     * @return 操作集合
     */
    public List<Sys_Permission> getMenuOperation(Integer pid);

    public List<Object> FindAll(String HQL) ;

    public List<Object> findByPage(String hql,int pagesize,int offset);

    public List listByHQL(String hql) ;

}
