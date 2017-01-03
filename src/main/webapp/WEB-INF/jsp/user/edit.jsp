<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>用户列表</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
  <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrapValidator.min.css">
  <script src="${pageContext.request.contextPath}/resources/js/bootstrapValidator.min.js"></script>

  <script src="${pageContext.request.contextPath}/resources/js/bootstrap-multiselect.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-multiselect.css">
</head>
<body>

<%--路径导航--%>
<div>
  <ol class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/admin/user/users">Users</a></li>
    <li class="active">Edit</li>
  </ol>
</div>


<div>
  <sf:form modelAttribute="userDto" id="userform" role="form">
    <div class="form-group">
      <label>用户名</label>
      <c:if test="${add}">
        <sf:input path="username" cssClass="form-control"/>
        <sf:errors path="username"/>
        <sf:errors title="username"/>
      </c:if>
      <c:if test="${not add}">
        <sf:input path="username" cssClass="form-control" readonly="true"
                  placeholder="${user.username}"/>
      </c:if>
    </div>

    <div class="form-group">
      <c:if test="${add}">
        <label>密码</label>
        <sf:password path="password" cssClass="form-control"/><sf:errors path="password"/>
      </c:if>

      <c:if test="${not add}">
        <sf:hidden path="password"/>
      </c:if>
    </div>

    <div class="form-group">
      <lable>Email</lable>
      <sf:input path="email" cssClass="form-control"/> <sf:errors path="email"/>
    </div>

    <div class="form-group">
      <div class="radio-inline">
        <lable>
          <sf:radiobutton path="sex" checked="true" value="1"/>男
        </lable>
      </div>
      <div class="radio-inline">
        <lable>
          <sf:radiobutton path="sex" value="0"/>女
        </lable>
      </div>
      <sf:errors path="sex"/>
    </div>

    <div class="form-group">
      <div class="checkbox">
        <label><sf:checkbox path="admin" value="1" cssClass="checkbox-inline"/>超级管理员</label>
        <sf:errors path="admin"/><br>
      </div>
    </div>

    <div class="form-group">
      <label>选择权限</label> <br>
      <%--<sf:checkboxes path="pids" items="${permissions}" itemLabel="name" itemValue="id"/>--%>
      <sf:select path="pids" items="${permissions}" itemLabel="name" itemValue="id"
                 id="select-pids" multiple="multiple"/>
    </div>

    <div class="form-group">
      <label>选择角色组</label> <br>
      <%--<sf:checkboxes path="gids" items="${groups}" itemLabel="name" itemValue="id"/>--%>
      <sf:select path="gids" items="${groups}" itemLabel="name" itemValue="id"
                 id="select-gids" multiple="multiple"/>
    </div>

    <div class="form-group">
      <button type="submit" class="btn btn-default">提交</button>
    </div>

  </sf:form>
</div>

<script>
  $(document).ready(function () {
    $("#userform").bootstrapValidator({
      fields: {
        username: {
          message: '用戶名不合法',
          validators: {
            notEmpty: {
              message: '用户名不能为空'
            },
            stringLength: {
              min: 6,
              max: 30,
              message: '用户名长度必须在6到30之间'
            }
          }
        },
        password: {
          message: '密码不合法',
          validators: {
            notEmpty: {
              message: '密码不能为空'
            },
            stringLength: {
              min: 6,
              max: 30,
              message: '密码长度必须在6到30之间'
            }
          }
        },
      }
    })
  });
</script>

<script type="text/javascript">
  $(document).ready(function() {
    $('#select-pids').multiselect({
      includeSelectAllOption: true,
      enableFiltering: true
    });
    $('#select-gids').multiselect({
      includeSelectAllOption: true,
      enableFiltering: true
    });
  });
</script>

</body>
</html>
