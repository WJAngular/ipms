package com.mtd.dao;

/**
 * Created by Wooce Yang on 2015/12/18.
 */
import java.util.List;

import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_UserRole;
import org.springframework.stereotype.Repository;


/**
 * �û���ɫDAO
 * @date 2015��1��13��
 */
@Repository
public interface UserRoleDao{
    public void save(Object object);

    public void update(Object object);

    public void delete(Object object);

    /**
     * ɾ���û���ɫ
     * @param userId
     * @param roleId
     */
    public void deleteUR(Integer userId,Integer roleId);

    /**
     * ��ѯ�û�ӵ�еĽ�ɫid����
     * @param userId
     * @return �������
     */
    @SuppressWarnings("unchecked")
    public List<Integer> findRoleIds(Integer userId);

    public List<Sys_UserRole> findRoles(Integer userId);

    public boolean hasRole(Integer roleId);
}
