package com.mtd.entity;

/**
 * Created by Wooce Yang on 2015/12/12.
 */
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 权限 entity
 */
@Entity(name = "Sys_Permission")
public class Sys_Permission implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer pid;
    private String name;
    private String type;
    private Integer sort;
    private String url;
    private String icon;
    private String permCode;
    private String description;
    private String status;
    @JsonIgnore
    private Set<Sys_RolePermission> rolePermissions = new HashSet<Sys_RolePermission>(0);

    // Constructors

    /** default constructor */
    public Sys_Permission() {
    }

    /** minimal constructor */
    public Sys_Permission(String name) {
        this.name = name;
    }

    public Sys_Permission(Integer id) {
        this.id=id;
    }

    public Sys_Permission (Integer id,Integer pid,String name){
        this.id=id;
        this.pid=pid;
        this.name=name;
    }

    public Sys_Permission (Integer pid,String name, String type,String url,String permCode){
        this.pid=pid;
        this.name=name;
        this.type=type;
        this.url=url;
        this.permCode=permCode;
    }

    /** full constructor */
    public Sys_Permission(Integer pid, String name, String type, Integer sort,
                      String url, String icon, String permCode, String description,
                      String status, Set<Sys_RolePermission> rolePermissions) {
        this.pid = pid;
        this.name = name;
        this.type = type;
        this.sort = sort;
        this.url = url;
        this.icon = icon;
        this.permCode = permCode;
        this.description = description;
        this.status=status;
        this.rolePermissions = rolePermissions;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "pid")
    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", length = 20)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "icon")
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "permCode", length = 50)
    public String getPermCode() {
        return this.permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    @Column(name = "description", length = 8000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permission")
    public Set<Sys_RolePermission> getRolePermissions() {
        return this.rolePermissions;
    }

    public void setRolePermissions(Set<Sys_RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    @Column(name = "status", length = 1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String state) {
        this.status = state;
    }


}