package org.yxm.cms.model;

import javax.persistence.*;

/**
 * Created by yxm on 2016.12.29.
 */
@Entity
@Table(name = "t_user_permission")
public class UserPermission {
    private Integer id;
    private User user;
    private Permission permission;

    public UserPermission() {
    }

    public UserPermission(User user, Permission permission) {
        this.user = user;
        this.permission = permission;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "pid")
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
