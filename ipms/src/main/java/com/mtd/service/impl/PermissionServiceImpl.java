package com.mtd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtd.dao.PermissionDao;
import com.mtd.entity.Sys_Permission;
import com.mtd.service.PermissionService;

/**
 * Created by Wooce Yang on 2015/12/19.
 */
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PermissionDao getEntityDao(){
        return permissionDao;
    }

    public void setPermissionDao(PermissionDao dao) {
        this.permissionDao = dao;
    }

    public Sys_Permission get(Integer id){
        return (Sys_Permission)permissionDao.findByID(Sys_Permission.class, id);
    }

    public void save(Object object) {
        permissionDao.save(object);
    }

    public void update(Object object) {
        permissionDao.update(object);
    }

    public void delete(Object object) {
        permissionDao.delete(object);
    }

    public List<Sys_Permission> getAll(){
        return getEntityDao().findAll();
    }

    /**
     * ��Ӳ˵���������
     * @param pid �˵�id
     * @param pClassName �˵�Ȩ�ޱ�ʶ��
     */
    @Transactional(readOnly = false)
    public void addBaseOpe(Integer pid,String pClassName){
        List<Sys_Permission> pList=new ArrayList<Sys_Permission>();
        pList.add(new Sys_Permission(pid, "���", "O", "", "sys:"+pClassName+":add"));
        pList.add(new Sys_Permission(pid, "ɾ��", "O", "", "sys:"+pClassName+":delete"));
        pList.add(new Sys_Permission(pid, "�޸�", "O", "", "sys:"+pClassName+":update"));
        pList.add(new Sys_Permission(pid, "�鿴", "O", "", "sys:"+pClassName+":view"));

        //���û�еĻ�������
        List<Sys_Permission> existPList=getMenuOperation(pid);
        for(Sys_Permission permission:pList){
            boolean exist=false;
            for(Sys_Permission existPermission:existPList){
                if(permission.getPermCode().equals(existPermission.getPermCode())){
                    exist=true;
                    break;
                }else{
                    exist=false;
                }
            }
            if(!exist)
                permissionDao.save(permission);
        }
    }

    /**
     * ��ȡ��ɫӵ�е�Ȩ�޼���
     * @param userId
     * @return �������
     */
    public List<Sys_Permission> getPermissions(Integer userId){
        return permissionDao.findPermissions(userId);
    }

    /**
     * ��ȡ��ɫӵ�еĲ˵�
     * @param userId
     * @return �˵�����
     */
    public List<Sys_Permission> getMenus(Integer userId){
        return permissionDao.findMenus(userId);
    }

    /**
     * ��ȡ���в˵�
     * @return �˵�����
     */
    public List<Sys_Permission> getMenus(){
        return permissionDao.findMenus();
    }

    public List<Sys_Permission> getMenus(int pagesize, int offset){ return permissionDao.findMenus(pagesize,offset); }

    /**
     * ��ȡ�˵��µĲ���
     * @param pid
     * @return ��������
     */
    public List<Sys_Permission> getMenuOperation(Integer pid){
        return permissionDao.findMenuOperation(pid);
    }

    public List<Object> FindAll(String HQL) {
        return permissionDao.FindAll(HQL);
    }

    public List<Object> findByPage(String hql, int pagesize, int offset) {
        return permissionDao.findByPage(hql, pagesize, offset);
    }

    @SuppressWarnings("rawtypes")
    public List listByHQL(String hql) {
        return permissionDao.listByHQL(hql);
    }

}
