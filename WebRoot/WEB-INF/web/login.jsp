<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function change(img) {
		img.src = img.src + "?" + new Date().getTime();
	}
</script>
<title>登陆</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/clogin" method="post">
	用户名：
	<input type="text" name="username" />
	<br /> 密 码：
	<input type="text" name="password" />
	<br/>  验证码：
	<input type="text" name="checkimg" />
	<img alt="验证码" onclick="change(this)" src="${pageContext.request.contextPath}/checkimage"/>
	<br/>
	<input type="submit" value="登陆"/>
	<input type="hidden" name="token" value="${token}">
</form>
</body>
</html>