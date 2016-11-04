package com.mtd.service;

import com.mtd.dao.UserRoleDao;
import com.mtd.entity.Sys_UserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Wooce Yang on 2015/12/20.
 */
public interface UserRoleService {
    public UserRoleDao getEntityDao();

    /**
     * ����޸��û���ɫ
     *
     * @param userId
     * @param oldList
     * @param newList
     */
    @Transactional(readOnly = false)
    public void updateUserRole(Integer userId, List<Integer> oldList,List<String> newList);

    /**
     * ��ȡ�û�ӵ�н�ɫid����
     *
     * @param userId
     * @return �������
     */
    public List<Integer> getRoleIdList(Integer userId);

    public List<Sys_UserRole> getRoles(Integer userId);

    public boolean hasRole(Integer roleId);
}
