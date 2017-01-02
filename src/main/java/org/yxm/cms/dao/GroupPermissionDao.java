package org.yxm.cms.dao;

import org.springframework.stereotype.Repository;
import org.yxm.cms.model.GroupPermission;

import java.util.List;

/**
 * Created by yxm on 2016.12.29.
 */
@Repository
public class GroupPermissionDao extends BaseDao<GroupPermission> {
  public GroupPermission loadGroupPermission(Integer gid, Integer pid) {
    String hql = "from GroupPermission gp where gp.group.id = ? and gp.permission.id = ?";
    return (GroupPermission) this.queryObject(hql, new Object[]{gid, pid});
  }

  public List<Integer> listGroupPids(Integer gid) {
    String hql = "select gp.permission.id from GroupPermission gp where gp.group.id = ? ";
    return this.getSession().createQuery(hql)
            .setParameter(0, gid).list();
  }
}
