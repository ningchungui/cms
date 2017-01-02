package org.yxm.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yxm.cms.base.Constant;
import org.yxm.cms.model.Group;
import org.yxm.cms.model.Permission;
import org.yxm.cms.model.User;
import org.yxm.cms.service.GroupService;
import org.yxm.cms.service.UserService;
import org.yxm.cms.util.ListUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yxm on 2017.01.02.
 */
@Controller
@RequestMapping(value = "")
public class LoginController {

  @Autowired
  private UserService userService;
  @Autowired
  private GroupService groupService;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login/login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@RequestParam String username, @RequestParam String password,
                      Model model, HttpServletRequest request) {
    User user = userService.findByUserNamePassword(username, password);
    if (user == null) {
      model.addAttribute("login_error", "登录名或密码错误！");
      return "login/login";
    }

    List<Permission> permissions = userService.listUserPermissions(user.getId());
    List<Integer> gids = userService.listUserGids(user.getId());
    List<Permission> groupPermissions = groupService.listGroupsPermissions(ListUtil.list2array(gids));

    for (Permission p : groupPermissions) {
      if (!permissions.contains(p)) {
        permissions.add(p);
      }
    }

    request.getSession().setAttribute(Constant.LOGIN_USER, user);
    request.getSession().setAttribute(Constant.LOGIN_PERMISSIONS, permissions);

    return "redirect:/admin/index";
  }

  @RequestMapping("/logout")
  public String logout(HttpServletRequest request) {
    request.getSession().removeAttribute(Constant.LOGIN_USER);
    request.getSession().removeAttribute(Constant.LOGIN_PERMISSIONS);
    return "redirect:/login";
  }
}
