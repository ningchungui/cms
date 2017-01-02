package org.yxm.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxm.cms.dao.PermissionDao;
import org.yxm.cms.model.Group;
import org.yxm.cms.model.Pager;
import org.yxm.cms.model.Permission;

import java.util.List;

/**
 * Created by yxm on 2016.12.29.
 */
@Service
public class PermissionService {

  @Autowired
  private PermissionDao permissionDao;

  public Pager<Permission> find() {
    return permissionDao.findAll();
  }

  public void add(Permission permission) {
    permissionDao.add(permission);
  }

  public void delete(Integer pid) {
    permissionDao.delete(pid);
  }

  public Permission load(Integer pid) {
    return permissionDao.load(pid);
  }

  public void update(Permission permission) {
    permissionDao.update(permission);
  }

  public List<Permission> list() {
    return permissionDao.listAll();
  }
}
