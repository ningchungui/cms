<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>页面管理</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
  <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css">

</head>
<body>

<%--路径导航--%>
<div>
  <ol class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/admin/page/pages">Pages</a></li>
    <li class="active">Edit</li>
  </ol>
</div>

<div>
  <sf:form modelAttribute="page" role="form">
    <div class="form-group">
      <label>标题</label>
      <sf:input path="title" cssClass="form-control"/> <sf:errors path="title"/>
    </div>

    <div class="form-group">
      <label>描述</label>
      <sf:input path="description" cssClass="form-control"/> <sf:errors path="description"/>
    </div>

    <div class="form-group">
      <label>类型</label>
      <sf:select path="contentType">
        <sf:options items="${contentTypes}"/>
      </sf:select>
    </div>

    <div class="form-group">
      <label>显示在上部menu</label>
      <sf:checkbox path="inTopMenu"/>
    </div>

    <div class="form-group">
      <label>显示在左部menu</label>
      <sf:checkbox path="inLeftMenu"/>
    </div>

    <div class="form-group">
      <label>是否显示</label>
      <sf:checkbox path="status"/>
    </div>

    <div class="form-group">
      <label>父级Page</label>
      <sf:select path="parent">
        <sf:options items="${parents}" itemLabel="title" itemValue="id"/>
      </sf:select>
    </div>

    <div class="form-group">
      <button type="submit" class="btn btn-default">提交</button>
    </div>
  </sf:form>
</div>

<script type="text/javascript">
  $(document).ready(function () {
    $('#example-post').multiselect({
      includeSelectAllOption: true,
      enableFiltering: true
    });
  });
</script>

</body>
</html>
