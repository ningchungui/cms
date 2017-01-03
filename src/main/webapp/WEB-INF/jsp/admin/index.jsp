<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>后台管理首页</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">

  <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body style="background: #ffffff;">

<%--占位--%>
<nav class="navbar navbar-default"></nav>
<%--导航--%>
<nav data-spy="scroll" class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand glyphicon glyphicon-cloud"></a>
      <a class="navbar-brand" href="#">后台管理首页</a>
    </div>

    <p class="navbar-text">欢迎登录</p>

    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">Link</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${LOGIN_USER.username}<span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
          <li><a href="#">绑定邮箱</a></li>
          <li><a href="#">更新密码</a></li>
          <li class="divider"></li>
          <li><a href="${pageContext.request.contextPath}/logout">注销</a></li>
        </ul>
      </li>
    </ul>

  </div>
</nav>

<div class="container-fluid">
  <div class="col-md-2">
    <jsp:include page="index_left.jsp"/>
  </div>

  <div class="col-md-10">
    <%--<iframe src="${pageContext.request.contextPath}/admin/defaultPage" width="100%" height="600px" id="frame_content"
            scrolling="no" name="content" frameborder="0"
            onload="this.height=this.contentWindow.document.documentElement.scrollHeight">
    </iframe>--%>

    <iframe src="${pageContext.request.contextPath}/admin/defaultPage" width="100%" id="frame_content"
            name="content" frameborder="0"
            onload="reinitframe()">
    </iframe>

  </div>
</div>

<%--解决 iframe 高度自适应--%>
<script>
  function reinitframe() {
    var iframe = document.getElementById("frame_content");
    try {
      var bHeight = iframe.contentWindow.document.body.scrollHeight;
      var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
      var height = Math.max(bHeight, dHeight);

      iframe.height = height;
    }
    catch (ex) {
      alert(ex);
    }
  }
</script>
</body>
</html>
