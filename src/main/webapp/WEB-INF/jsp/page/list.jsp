<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>页面列表</title>

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
    <li><a href="${pageContext.request.contextPath}/admin/page/pages">Pages</a></li>
  </ol>
</div>

<div>
  <p><a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/admin/page/add">新建</a></p>

  <table class="table table-bordered table-hovert text-center">
    <tr>
      <td>标题</td>
      <td>描述</td>
      <td>内容类型</td>
      <td>顶级列表</td>
      <td>左侧列表</td>
      <td>顺序</td>
      <td>父级ID</td>
      <td>是否启用</td>
      <td>操作</td>
    </tr>

    <c:forEach items="${datas.datas}" var="page">
      <tr>
        <td>${page.title}</td>
        <td>${page.description}</td>
        <td>${page.contentType.name}</td>
        <td>${page.inTopMenu}</td>
        <td>${page.inLeftMenu}</td>
        <td>${page.order}</td>
        <td>${page.parent.id}</td>
        <td>${page.status}</td>
        <td>
          <a type="button" class="btn btn-primary btn-sm"
             href="${pageContext.request.contextPath}/admin/page/update/${page.id}">修改
          </a>
          <a type="button" class="btn btn-danger btn-sm"
             href="${pageContext.request.contextPath}/admin/page/delete/${page.id}">删除
          </a>
        </td>
      </tr>

    </c:forEach>
  </table>

  <%--分页--%>
  <jsp:include page="/resources/jsp/bootstrap_pager.jsp">
    <jsp:param value="${datas.total }" name="totalRecord"/>
    <jsp:param value="pages" name="url"/>
  </jsp:include>
</div>
</body>
</html>
