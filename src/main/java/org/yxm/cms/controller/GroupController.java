package org.yxm.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yxm.cms.dto.GroupDto;
import org.yxm.cms.model.Group;
import org.yxm.cms.service.GroupService;
import org.yxm.cms.service.PermissionService;
import org.yxm.cms.util.ListUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yxm on 2016.12.29.
 */
@Controller
@RequestMapping(value = "/admin/group")
public class GroupController {
  @Autowired
  private GroupService groupService;
  @Autowired
  private PermissionService permissionService;

  @RequestMapping(value = "/groups")
  public String list(Model model) {
    model.addAttribute("datas", groupService.find());
    return "group/list";
  }

  public void initPermissions(Model model) {
    model.addAttribute("permissions", permissionService.list());
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    initPermissions(model);
    model.addAttribute("groupDto", new GroupDto());
    return "group/edit";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String add(Model model, @Validated GroupDto groupDto, BindingResult br) {
    if (br.hasErrors()) {
      return "group/edit";
    }
    Group group = groupDto.getGroup();
    groupService.add(group);
    groupService.addGroupPermissions(group.getId(), Arrays.asList(groupDto.getPids()));
    return "redirect:/admin/group/groups";
  }

  @RequestMapping(value = "/delete/{gid}", method = RequestMethod.GET)
  public String delete(@PathVariable Integer gid) {
    groupService.delete(gid);
    return "redirect:/admin/group/groups";
  }

  @RequestMapping(value = "/update/{gid}", method = RequestMethod.GET)
  public String update(Model model, @PathVariable Integer gid) {
    Group group = groupService.load(gid);
    List<Integer> pids = groupService.listGroupPids(gid);
    GroupDto groupDto = new GroupDto(group, ListUtil.list2array(pids));

    initPermissions(model);
    model.addAttribute("groupDto", groupDto);
    return "group/edit";
  }

  @RequestMapping(value = "/update/{gid}", method = RequestMethod.POST)
  public String update(Model model, @PathVariable Integer gid, @Validated GroupDto groupDto,
                       BindingResult br) {
    if (br.hasErrors()) {
      return "group/edit";
    }

    Group old = groupService.load(gid);
    old.setName(groupDto.getName());
    old.setDescription(groupDto.getDescription());

    groupService.update(old);
    groupService.updateGroupPermissions(gid, Arrays.asList(groupDto.getPids()));
    return "redirect:/admin/group/groups";
  }
}
