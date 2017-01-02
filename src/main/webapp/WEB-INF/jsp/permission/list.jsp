<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>权限管理</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
  <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>

<%--路径导航--%>
<div>
  <ol class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/admin/permission/permissions">Permission</a></li>
  </ol>
</div>

<div>
  <p><a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/admin/permission/add">新建</a>
  </p>

  <table class="table table-bordered table-hovert text-center">
    <tr>
      <td>权限名称</td>
      <td>描述</td>
      <td>URL</td>
      <td>操作</td>
    </tr>

    <c:forEach items="${datas.datas}" var="permission">
      <tr>
        <td>${permission.name}</td>
        <td>${permission.description}</td>
        <td>${permission.url}</td>
        <td>
          <a type="button" class="btn btn-primary btn-sm"
             href="${pageContext.request.contextPath}/admin/permission/update/${permission.id}">修改
          </a>
          <a type="button" class="btn btn-danger btn-sm"
             href="${pageContext.request.contextPath}/admin/permission/delete/${permission.id}">删除
          </a>
        </td>
      </tr>

    </c:forEach>
  </table>

  <%--分页--%>
  <jsp:include page="/resources/jsp/bootstrap_pager.jsp">
    <jsp:param value="${datas.total }" name="totalRecord"/>
    <jsp:param value="permissions" name="url"/>
  </jsp:include>
</div>
</body>
</html>
