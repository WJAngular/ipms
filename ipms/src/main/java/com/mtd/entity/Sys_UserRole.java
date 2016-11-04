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
 * 用户角色entity
 * @date 2015年1月13日
 */
@Entity(name = "Sys_UserRole")
public class Sys_UserRole implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Sys_Users user;
    private Sys_Role role;

    // Constructors

    /** default constructor */
    public Sys_UserRole() {
    }

    /** full constructor */
    public Sys_UserRole(Sys_Users user, Sys_Role role) {
        this.user = user;
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
    public Sys_Users getUser() {
        return this.user;
    }

    public void setUser(Sys_Users user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Sys_Role getRole() {
        return this.role;
    }

    public void setRole(Sys_Role role) {
        this.role = role;
    }

}