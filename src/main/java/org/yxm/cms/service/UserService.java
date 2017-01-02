package org.yxm.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxm.cms.dao.*;
import org.yxm.cms.model.*;

import java.util.List;

/**
 * Created by yxm on 2016.12.28.
 */
@Service
public class UserService {

  @Autowired
  private UserDao userDao;
  @Autowired
  private PermissionDao permissionDao;
  @Autowired
  private GroupDao groupDao;
  @Autowired
  private UserPermissionDao userPermissionDao;
  @Autowired
  private UserGroupDao userGroupDao;

  public List<User> list() {
    return userDao.listAll();
  }

  public Pager<User> find() {
    return userDao.findAll();
  }

  public void add(User user) {
    userDao.add(user);
  }

  public void delete(Integer uid) {
    userDao.delete(uid);
  }

  public User load(Integer uid) {
    return userDao.load(uid);
  }

  public void update(User old) {
    userDao.update(old);
  }

  public User findByUserName(String username) {
    return userDao.loadByUserName(username);
  }

  public void addUserPermissions(Integer uid, List<Integer> pids) {
    for (Integer pid : pids) {
      this.addUserPermission(uid, pid);
    }
  }

  private void addUserPermission(Integer uid, Integer pid) {
    UserPermission up = userPermissionDao.load(uid, pid);
    if (up != null) {
      return;
    }
    up = new UserPermission(userDao.load(uid), permissionDao.load(pid));
    userPermissionDao.add(up);
  }

  public void addUserGroups(Integer uid, List<Integer> gids) {
    for (Integer gid : gids) {
      this.addUserGroup(uid, gid);
    }
  }

  private void addUserGroup(Integer uid, Integer gid) {
    UserGroup ug = userGroupDao.load(uid, gid);
    if (ug != null) {
      return;
    }
    ug = new UserGroup(userDao.load(uid), groupDao.load(gid));
    userGroupDao.add(ug);
  }

  public List<Integer> listUserPids(Integer uid) {
    return userDao.listUserPids(uid);
  }

  public List<Permission> listUserPermissions(Integer uid) {
    return userDao.listUserPermissions(uid);
  }

  public List<Integer> listUserGids(Integer uid) {
    return userDao.listUserGids(uid);
  }

  public void updateUserPermissions(Integer uid, List<Integer> pids) {
    List<Integer> oldIds = this.listUserPids(uid);

    for (Integer pid : oldIds) {
      if (!pids.contains(pid)) {
        this.deleteUserPermission(uid, pid);
      }
    }

    for (Integer pid : pids) {
      if (!oldIds.contains(pid)) {
        this.addUserPermission(uid, pid);
      }
    }
  }

  private void deleteUserPermission(Integer uid, Integer pid) {
    UserPermission up = userPermissionDao.load(uid, pid);
    if (up == null) {
      return;
    }
    userPermissionDao.delete(up.getId());
  }

  public void updateUserGroups(Integer uid, List<Integer> gids) {
    List<Integer> oldIds = this.listUserGids(uid);

    for (Integer gid : gids) {
      if (!oldIds.contains(gid)) {
        this.addUserGroup(uid, gid);
      }
    }

    for (Integer gid : oldIds) {
      if (!gids.contains(gid)) {
        this.deleteUserGroup(uid, gid);
      }
    }
  }

  private void deleteUserGroup(Integer uid, Integer gid) {
    UserGroup ug = userGroupDao.load(uid, gid);
    if (ug == null) {
      return;
    }
    userGroupDao.delete(ug.getId());
  }

  public User findByUserNamePassword(String username, String password) {
    return userDao.loadByUserNamePassword(username, password);
  }

  public List<Group> listUserGroups(Integer uid) {
    return userDao.listUserGroups(uid);
  }
}
