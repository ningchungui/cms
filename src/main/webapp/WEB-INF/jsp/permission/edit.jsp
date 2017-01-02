<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>权限管理</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>

<%--路径导航--%>
<div>
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/admin/permission/permissions">Permission</a></li>
        <li class="active">Edit</li>
    </ol>
</div>

<div>
    <sf:form modelAttribute="permission" role="form">
        <div class="form-group">
            <label>权限名称</label>
            <sf:input path="name" cssClass="form-control"/> <sf:errors path="name"/>
        </div>

        <div class="form-group">
            <label>描述</label>
            <sf:input path="description" cssClass="form-control"/> <sf:errors path="description"/>
        </div>

        <div class="form-group">
            <label>地址</label>
            <sf:input path="url" cssClass="form-control"/> <sf:errors path="url"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </sf:form>
</div>
</body>
</html>
