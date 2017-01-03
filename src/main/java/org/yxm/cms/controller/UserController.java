package org.yxm.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yxm.cms.dto.UserDto;
import org.yxm.cms.model.User;
import org.yxm.cms.service.GroupService;
import org.yxm.cms.service.PermissionService;
import org.yxm.cms.service.UserService;
import org.yxm.cms.util.ListUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by yxm on 2016.12.28.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private GroupService groupService;
  @Autowired
  private PermissionService permissionService;

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String list(Model model) {
    model.addAttribute("datas", userService.find());
    return "user/list";
  }

  private void initPermissions(Model model) {
    model.addAttribute("permissions", permissionService.list());
  }

  private void initGroups(Model model) {
    model.addAttribute("groups", groupService.list());
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    model.addAttribute("add", true);
    model.addAttribute("userDto", new UserDto());
    initPermissions(model);
    initGroups(model);

    return "user/edit";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String add(Model model, @Validated UserDto userDto, BindingResult br) {
    User temp = userService.findByUserName(userDto.getUsername());
    if (temp != null) {
      br.addError(new ObjectError("username", "用户名已经存在"));
    }

    if (br.hasErrors()) {
      model.addAttribute("add", true);
      initPermissions(model);
      initGroups(model);
      return "user/edit";
    }
    userDto.setCreateDate(new Date());

    User user = userDto.getUser();
    userService.add(user);

    userService.addUserPermissions(user.getId(), Arrays.asList(userDto.getPids()));
    userService.addUserGroups(user.getId(), Arrays.asList(userDto.getGids()));
    return "redirect:/admin/user/users";
  }

  @RequestMapping(value = "/delete/{uid}", method = RequestMethod.GET)
  public String delete(@PathVariable Integer uid) {
    userService.delete(uid);
    return "redirect:/admin/user/users";
  }

  @RequestMapping(value = "/update/{uid}", method = RequestMethod.GET)
  public String update(@PathVariable Integer uid, Model model) {
    User user = userService.load(uid);
    List<Integer> pids = userService.listUserPids(uid);
    List<Integer> gids = userService.listUserGids(uid);
    UserDto userDto = new UserDto(user, ListUtil.list2array(pids), ListUtil.list2array(gids));

    model.addAttribute("userDto", userDto);
    initPermissions(model);
    initGroups(model);

    return "user/edit";
  }

  @RequestMapping(value = "/update/{uid}", method = RequestMethod.POST)
  public String update(@PathVariable Integer uid, @Validated UserDto userDto,
                       BindingResult br, Model model) {
    if (br.hasErrors()) {
      initPermissions(model);
      initGroups(model);
      return "user/edit";
    }
    User old = userService.load(uid);
    old.setEmail(userDto.getEmail());
    old.setAdmin(userDto.getAdmin());
    old.setSex(userDto.getSex());
    userService.update(old);

    userService.updateUserPermissions(uid, Arrays.asList(userDto.getPids()));
    userService.updateUserGroups(uid, Arrays.asList(userDto.getGids()));
    return "redirect:/admin/user/users";
  }
}
