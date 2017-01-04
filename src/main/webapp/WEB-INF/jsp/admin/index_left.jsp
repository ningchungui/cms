<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">--%>
<%--<link href="${pageContext.request.contextPath}/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css"--%>
<%--rel="stylesheet">--%>
<link href="${pageContext.request.contextPath}/resources/sb-admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css"
      rel="stylesheet" type="text/css">

<%--
<ul class="nav nav-pills nav-stacked" role="tablist">
    <li><a target="_self" href="${pageContext.request.contextPath}/admin/index">首页</a></li>
    <li><a target="content" href="${pageContext.request.contextPath}/admin/user/users">用户管理</a></li>
    <li><a target="content" href="${pageContext.request.contextPath}/admin/group/groups">用户组管理</a></li>
    <li><a target="content" href="${pageContext.request.contextPath}/admin/permission/permissions">权限管理</a></li>
</ul>
--%>

<ul class="nav nav-pills nav-stacked" role="tablist" id="side-menu" style=" background-color: #F5F5F5;" >
  <li>
    <a target="_self" href="${pageContext.request.contextPath}/admin/index"><i
      class="fa fa-bar-chart-o fa-fw"></i>首页</a>
  </li>
  <li>
    <a href="#"><i class="fa fa-wrench fa-fw"></i>用户管理<span class="fa arrow"></span></a>
    <ul class="nav nav-second-level">
      <li>
        <a target="content" href="${pageContext.request.contextPath}/admin/user/users">用户管理</a>
      </li>
      <li>
        <a target="content" href="${pageContext.request.contextPath}/admin/group/groups">用户组管理</a>
      </li>
      <li>
        <a target="content"
           href="${pageContext.request.contextPath}/admin/permission/permissions">权限管理</a>
      </li>
    </ul>
  </li>
  <li>
    <a target="content" href="${pageContext.request.contextPath}/admin/page/pages"><i
      class="fa fa-bar-chart-o fa-fw"></i>页面管理</a>
  </li>
</ul>

<%--<script src="${pageContext.request.contextPath}/resources/sb-admin/vendor/jquery/jquery.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/sb-admin/dist/js/sb-admin-2.js"></script>