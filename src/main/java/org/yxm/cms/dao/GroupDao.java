package org.yxm.cms.dao;

import org.springframework.stereotype.Repository;
import org.yxm.cms.model.Group;
import org.yxm.cms.model.GroupPermission;
import org.yxm.cms.model.Permission;

import java.util.Collections;
import java.util.List;

/**
 * Created by yxm on 2016.12.29.
 */
@Repository
public class GroupDao extends BaseDao<Group> {

  public List<Permission> listGroupsPermissions(Integer[] gids) {
    if (gids == null || gids.length <= 0) {
      return Collections.emptyList();
    }
    String hql = "select gp.permission from GroupPermission gp where gp.group.id in (:gids)";
    return this.getSession().createQuery(hql)
            .setParameterList("gids", gids).list();
  }
}
