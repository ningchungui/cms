package org.yxm.cms.dao;

import org.springframework.stereotype.Repository;
import org.yxm.cms.model.Group;
import org.yxm.cms.model.Permission;
import org.yxm.cms.model.User;

import java.util.List;

/**
 * Created by yxm on 2016.12.28.
 */
@Repository
public class UserDao extends BaseDao<User> {
  public User loadByUserName(String username) {
    String hql = "from User u where u.username=?";
    return (User) this.queryObject(hql, username);
  }

  public User loadByUserNamePassword(String username, String password) {
    String hql = "from User u where u.username=? and u.password=?";
    return (User) this.queryObject(hql, new Object[]{username, password});
  }

  public List<Integer> listUserPids(Integer uid) {
    String hql = "select up.permission.id from UserPermission up where up.user.id = ?";
    return this.getSession().createQuery(hql)
            .setParameter(0, uid).list();
  }

  public List<Integer> listUserGids(Integer uid) {
    String hql = "select ug.group.id from UserGroup ug where ug.user.id = ?";
    return this.getSession().createQuery(hql)
            .setParameter(0, uid).list();
  }

  public List<Permission> listUserPermissions(Integer uid) {
    String hql = "select up.permission from UserPermission up where up.user.id = ?";
    return this.getSession().createQuery(hql)
            .setParameter(0, uid).list();
  }

  public List<Group> listUserGroups(Integer uid) {
    String hql = "select ug.group from UserGroup ug where ug.user.id = ?";
    return this.getSession().createQuery(hql)
            .setParameter(0, uid).list();
  }
}
