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
 * 角色entity
 */
@Entity(name = "Sys_Role")
public class Sys_Role implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String roleCode;
    private String description;
    private Short sort;
    private String delFlag;
    @JsonIgnore
    private Set<Sys_UserRole> userRoles = new HashSet<Sys_UserRole>(0);
    @JsonIgnore
    private Set<Sys_RolePermission> rolePermissions = new HashSet<Sys_RolePermission>(0);

    // Constructors

    /** default constructor */
    public Sys_Role() {
    }

    public Sys_Role(Integer id) {
        this.id=id;
    }

    /** minimal constructor */
    public Sys_Role(String name, String roleCode) {
        this.name = name;
        this.roleCode = roleCode;
    }

    /** full constructor */
    public Sys_Role(String name, String roleCode, String description, Short sort,
                String delFlag, Set<Sys_UserRole> userRoles,
                Set<Sys_RolePermission> rolePermissions) {
        this.name = name;
        this.roleCode = roleCode;
        this.description = description;
        this.sort = sort;
        this.delFlag = delFlag;
        this.userRoles = userRoles;
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

    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "roleCode", nullable = false, length = 20)
    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Column(name = "description", length = 8000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "sort")
    public Short getSort() {
        return this.sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    @Column(name = "delFlag", length = 1)
    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<Sys_UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<Sys_UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<Sys_RolePermission> getRolePermissions() {
        return this.rolePermissions;
    }

    public void setRolePermissions(Set<Sys_RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

}