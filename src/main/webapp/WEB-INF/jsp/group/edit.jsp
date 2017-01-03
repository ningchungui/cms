<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>用户组信息</title>

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
    <li><a href="${pageContext.request.contextPath}/admin/group/groups">Group</a></li>
    <li class="active">Edit</li>
  </ol>
</div>

<div>
  <sf:form modelAttribute="groupDto" role="form">
    <div class="form-group">
      <label>组名称</label>
      <sf:input path="name" cssClass="form-control"/> <sf:errors path="name"/>
    </div>

    <div class="form-group">
      <label>描述</label>
      <sf:input path="description" cssClass="form-control"/> <sf:errors path="description"/>
    </div>

    <div class="form-group">
      <label>选择权限</label> <br>
      <%--<sf:checkboxes path="pids" items="${permissions}" itemLabel="name" itemValue="id"/>--%>

      <sf:select path="pids" items="${permissions}" itemLabel="name" itemValue="id"
                 id="example-post" multiple="multiple"/>
    </div>

    <div class="form-group">
      <button type="submit" class="btn btn-default">提交</button>
    </div>
  </sf:form>
</div>

<script type="text/javascript">
  $(document).ready(function() {
    $('#example-post').multiselect({
      includeSelectAllOption: true,
      enableFiltering: true
    });
  });
</script>

</body>
</html>
