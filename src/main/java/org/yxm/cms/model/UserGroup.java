package org.yxm.cms.model;

import javax.persistence.*;

/**
 * Created by yxm on 2016.12.29.
 */
@Entity
@Table(name = "t_user_group")
public class UserGroup {
    private Integer id;
    private User user;
    private Group group;

    public UserGroup() {
    }

    public UserGroup(User user, Group group) {
        this.user = user;
        this.group = group;
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
    @JoinColumn(name = "gid")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
