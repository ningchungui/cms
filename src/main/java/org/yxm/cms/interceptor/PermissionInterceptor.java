package org.yxm.cms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.yxm.cms.base.Constant;
import org.yxm.cms.exception.CmsException;
import org.yxm.cms.model.Permission;
import org.yxm.cms.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yxm on 2017.01.02.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    String url = request.getRequestURL().toString();
    if (url.contains("/admin")) {
      List<Permission> permissions = (List<Permission>) request.getSession().getAttribute(Constant.LOGIN_PERMISSIONS);
      User user = (User) request.getSession().getAttribute(Constant.LOGIN_USER);

      if (permissions == null || user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
      }

      // 如果是admin，就不需要权限验证
      if (user.getAdmin()) {
        return true;
      }

      boolean hasPermission = false;
      for (Permission permission : permissions) {
        if (url.contains(permission.getUrl())) {
          hasPermission = true;
          break;
        }
      }

      if (!hasPermission) {
        throw new CmsException("没有权限访问：" + url);
      }
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }
}
