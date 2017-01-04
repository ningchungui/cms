package org.yxm.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yxm.cms.model.Page;
import org.yxm.cms.model.Pager;
import org.yxm.cms.service.PageService;

import java.util.List;

/**
 * Created by yxm on 2017.01.04.
 */
@Controller
@RequestMapping(value = "/admin/page")
public class PageController {

  @Autowired
  private PageService pageService;

  @RequestMapping(value = "/pages", method = RequestMethod.GET)
  public String list(Model model) {
    Pager<Page> pages = pageService.findAll();
    model.addAttribute("datas", pages);
    return "page/list";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    model.addAttribute("page", new Page());
    return "page/edit";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String add(@Validated Page page) {
    return "redirect:/admin/page/pages";
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable Integer id) {
    return "redirect:/admin/page/pages";
  }

  @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
  public String update(@PathVariable Integer id) {
    return "page/edit";
  }

  @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
  public String update(@PathVariable Integer id, Page page) {
    return "redirect:/admin/page/pages";
  }
}
