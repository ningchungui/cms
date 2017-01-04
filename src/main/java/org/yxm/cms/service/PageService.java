package org.yxm.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxm.cms.dao.PageDao;
import org.yxm.cms.model.Page;
import org.yxm.cms.model.Pager;

import java.util.List;

/**
 * Created by yxm on 2017.01.04.
 */
@Service
public class PageService {

  @Autowired
  private PageDao pageDao;

  public Pager<Page> findAll() {
    return pageDao.findAll();
  }


}
