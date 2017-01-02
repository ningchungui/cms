package org.yxm.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yxm on 2016.12.27.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

  @RequestMapping(value = "/index")
  public String index() {
    return "admin/index";
  }

  @RequestMapping(value = "/defaultPage")
  public String defaultPage() {
    return "admin/default";
  }
}



