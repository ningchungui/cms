package org.yxm.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.yxm.cms.model.Permission;
import org.yxm.cms.service.PermissionService;

/**
 * Created by yxm on 2016.12.29.
 */
@Controller
@RequestMapping(value = "/admin/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/permissions")
    public String list(Model model) {
        model.addAttribute("datas", permissionService.find());
        return "permission/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("permission", new Permission());
        return "permission/edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @Validated Permission permission, BindingResult br) {
        if (br.hasErrors()) {
            return "permission/edit";
        }

        permissionService.add(permission);
        return "redirect:/admin/permission/permissions";
    }

    @RequestMapping(value = "/delete/{pid}")
    public String delete(@PathVariable Integer pid) {
        permissionService.delete(pid);
        return "redirect:/admin/permission/permissions";
    }

    @RequestMapping(value = "/update/{pid}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Integer pid) {
        Permission permission = permissionService.load(pid);
        model.addAttribute("permission", permission);
        return "permission/edit";
    }

    @RequestMapping(value = "/update/{pid}", method = RequestMethod.POST)
    public String update(Model model, @PathVariable Integer pid, @Validated Permission permission,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "permission/edit";
        }

        Permission old = permissionService.load(pid);
        old.setName(permission.getName());
        old.setUrl(permission.getUrl());
        old.setDescription(permission.getDescription());
        permissionService.update(old);

        return "redirect:/admin/permission/permissions";
    }

}
