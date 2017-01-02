package org.yxm.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxm.cms.dao.GroupDao;
import org.yxm.cms.dao.GroupPermissionDao;
import org.yxm.cms.dao.PermissionDao;
import org.yxm.cms.model.Group;
import org.yxm.cms.model.GroupPermission;
import org.yxm.cms.model.Pager;
import org.yxm.cms.model.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxm on 2016.12.29.
 */
@Service
public class GroupService {

  @Autowired
  private GroupDao groupDao;
  @Autowired
  private PermissionDao permissionDao;
  @Autowired
  private GroupPermissionDao groupPermissionDao;

  public Pager<Group> find() {
    return groupDao.findAll();
  }

  public void add(Group group) {
    groupDao.add(group);
  }

  public void delete(Integer gid) {
    groupDao.delete(gid);
  }

  public Group load(Integer gid) {
    return groupDao.load(gid);
  }

  public void update(Group group) {
    groupDao.update(group);
  }

  public void addGroupPermissions(Integer gid, List<Integer> pids) {
    for (Integer pid : pids) {
      this.addGroupPermission(gid, pid);
    }
  }

  private void addGroupPermission(Integer gid, Integer pid) {
    GroupPermission gp = groupPermissionDao.loadGroupPermission(gid, pid);
    if (gp != null) {
      return;
    }
    gp = new GroupPermission(groupDao.load(gid), permissionDao.load(pid));
    groupPermissionDao.add(gp);
  }

  private void deleteGroupPermission(Integer gid, Integer pid) {
    GroupPermission gp = groupPermissionDao.loadGroupPermission(gid, pid);
    if (gp == null) {
      return;
    }
    groupPermissionDao.delete(gp.getId());
  }

  public void updateGroupPermissions(Integer gid, List<Integer> pids) {
    List<Integer> oldPids = this.listGroupPids(gid);

    for (Integer pid : oldPids) {
      if (!pids.contains(pid)) {
        this.deleteGroupPermission(gid, pid);
      }
    }

    for (Integer pid : pids) {
      if (!oldPids.contains(pid)) {
        this.addGroupPermission(gid, pid);
      }
    }
  }

  public List<Integer> listGroupPids(Integer gid) {
    return groupPermissionDao.listGroupPids(gid);
  }

  public List<Group> list() {
    return groupDao.listAll();
  }

  public List<Permission> listGroupsPermissions(Integer[] gids) {
    return groupDao.listGroupsPermissions(gids);
  }
}
