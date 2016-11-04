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
 * Ȩ��service
 * @date 2015��1��13��
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
     * ��Ӳ˵���������
     * @param pid �˵�id
     * @param pClassName �˵�Ȩ�ޱ�ʶ��
     */
    @Transactional(readOnly = false)
    public void addBaseOpe(Integer pid,String pClassName);

    /**
     * ��ȡ��ɫӵ�е�Ȩ�޼���
     * @param userId
     * @return �������
     */
    public List<Sys_Permission> getPermissions(Integer userId);

    /**
     * ��ȡ��ɫӵ�еĲ˵�
     * @param userId
     * @return �˵�����
     */
    public List<Sys_Permission> getMenus(Integer userId);

    /**
     * ��ȡ���в˵�
     * @return �˵�����
     */
    public List<Sys_Permission> getMenus();

    public List<Sys_Permission> getMenus(int pagesize, int offset);

    /**
     * ��ȡ�˵��µĲ���
     * @param pid
     * @return ��������
     */
    public List<Sys_Permission> getMenuOperation(Integer pid);

    public List<Object> FindAll(String HQL) ;

    public List<Object> findByPage(String hql,int pagesize,int offset);

    public List listByHQL(String hql) ;

}
