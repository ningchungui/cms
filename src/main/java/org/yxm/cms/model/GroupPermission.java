package org.yxm.cms.model;

import javax.persistence.*;

/**
 * Created by yxm on 2016.12.29.
 */
@Entity
@Table(name = "t_group_permission")
public class GroupPermission {

    private Integer id;
    private Group group;
    private Permission permission;

    public GroupPermission() {
    }

    public GroupPermission(Group group, Permission permission) {
        this.group = group;
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
    @JoinColumn(name = "gid")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
