package com.mtd.service.impl;

import com.mtd.dao.UserRoleDao;
import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_UserRole;
import com.mtd.entity.Sys_Users;
import com.mtd.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/20.
 */
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserRoleDao getEntityDao() {
        return userRoleDao;
    }

    public void setUserRoleDao(UserRoleDao dao) {
        this.userRoleDao = dao;
    }

    /**
     * ����޸��û���ɫ
     *
     * @param userId
     * @param oldList
     * @param newList
     */
    @Transactional(readOnly = false)
    public void updateUserRole(Integer userId, List<Integer> oldList,List<String> newList) {
        // �Ƿ�ɾ��
        for (int i = 0, j = oldList.size(); i < j; i++) {
            if (!newList.contains(oldList.get(i))) {
                userRoleDao.deleteUR(userId, oldList.get(i));
            }
        }

        // �Ƿ����
        for (int m = 0, n = newList.size(); m < n; m++) {
            if (!oldList.contains(newList.get(m))) {
                userRoleDao.save(getUserRole(userId, Integer.parseInt(newList.get(m))));
            }
        }
    }

    /**
     * ����UserRole
     *
     * @param userId
     * @param roleId
     * @return UserRole
     */
    private Sys_UserRole getUserRole(Integer userId, Integer roleId) {
        Sys_UserRole ur = new Sys_UserRole();
        ur.setUser(new Sys_Users(userId));
        ur.setRole(new Sys_Role(roleId));
        return ur;
    }

    /**
     * ��ȡ�û�ӵ�н�ɫid����
     *
     * @param userId
     * @return �������
     */
    public List<Integer> getRoleIdList(Integer userId) {
        return userRoleDao.findRoleIds(userId);
    }

    public List<Sys_UserRole> getRoles(Integer userId){
        return userRoleDao.findRoles(userId);
    }

    public boolean hasRole(Integer roleId){ return userRoleDao.hasRole(roleId); }

}
