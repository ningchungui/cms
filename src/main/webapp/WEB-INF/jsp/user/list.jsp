<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>用户列表</title>

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
    <li><a href="${pageContext.request.contextPath}/admin/user/users">Users</a></li>
  </ol>
</div>


<div>
  <p><a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/admin/user/add">新建</a></p>

  <table class="table table-bordered table-hovert text-center">
    <tr>
      <td>用户名</td>
      <td>密码</td>
      <td>邮箱</td>
      <td>性别</td>
      <td>创建日期</td>
      <td>管理员</td>
      <td>操作</td>
    </tr>

    <c:forEach items="${datas.datas}" var="user">
      <tr>
        <td>${user.username}</td>
        <td>${user.password}</td>
        <td>${user.email}</td>
        <td>
          <c:if test="${user.sex}">男</c:if>
          <c:if test="${! user.sex}">女</c:if>
        </td>
        <td>${user.createDate}</td>
        <td>
          <c:if test="${user.admin}">是</c:if>
          <c:if test="${! user.admin}">否</c:if>
        </td>
        <td>
          <a type="button" class="btn btn-primary btn-sm"
             href="${pageContext.request.contextPath}/admin/user/update/${user.id}">修改
          </a>
          <a type="button" class="btn btn-danger btn-sm"
             href="${pageContext.request.contextPath}/admin/user/delete/${user.id}">删除
          </a>
        </td>
      </tr>

    </c:forEach>
  </table>

  <%--分页--%>
  <jsp:include page="/resources/jsp/bootstrap_pager.jsp">
    <jsp:param value="${datas.total }" name="totalRecord"/>
    <jsp:param value="users" name="url"/>
  </jsp:include>
</div>
</body>
</html>
