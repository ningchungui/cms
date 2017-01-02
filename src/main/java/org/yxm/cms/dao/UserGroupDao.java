package org.yxm.cms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yxm.cms.model.UserGroup;

/**
 * Created by yxm on 2017.01.01.
 */
@Repository
public class UserGroupDao extends BaseDao<UserGroup> {
  public UserGroup load(Integer uid, Integer gid) {
    String hql = "from UserGroup ug where ug.user.id = ? and ug.group.id = ?";
    return (UserGroup) this.queryObject(hql, new Object[]{uid, gid});
  }
}
