package org.yxm.cms.dao;

import org.springframework.stereotype.Repository;
import org.yxm.cms.model.UserPermission;

/**
 * Created by yxm on 2017.01.01.
 */
@Repository
public class UserPermissionDao extends BaseDao<UserPermission> {
  public UserPermission load(Integer uid, Integer pid) {
    String hql = "from UserPermission up where up.user.id = ? and up.permission.id = ?";
    return (UserPermission) this.queryObject(hql, new Object[]{uid, pid});
  }
}
