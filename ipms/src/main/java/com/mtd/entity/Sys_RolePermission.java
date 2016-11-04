package com.mtd.entity;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Wooce Yang on 2015/12/12.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 角色权限entity
 */
@Entity(name = "Sys_RolePermission")
public class Sys_RolePermission implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Sys_Permission permission;
    private Sys_Role role;

    // Constructors

    /** default constructor */
    public Sys_RolePermission() {
    }

    /** full constructor */
    public Sys_RolePermission(Sys_Permission permission, Sys_Role role) {
        this.permission = permission;
        this.role = role;
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

    @ManyToOne(fetch = FetchType.LAZY)
    public Sys_Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Sys_Permission permission) {
        this.permission = permission;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Sys_Role getRole() {
        return this.role;
    }

    public void setRole(Sys_Role role) {
        this.role = role;
    }

}