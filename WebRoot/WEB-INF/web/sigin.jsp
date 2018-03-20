<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="laydate/laydate.js"></script>
<script type="text/javascript">
	function change(img) {
		img.src = img.src + "?" + new Date().getTime();
	}
</script>
<title>注册</title>

</head>
<body>
	<form action="${pageContext.request.contextPath}/cinfo" method="post">
		用户名： <input type="text" name="username" value="${form.username}" /><span>${form.erros.username}</span><br />
		密 码： <input type="password" name="password" value="${form.password}"/><span>${form.erros.password}</span>
			<br /> 密 码： <input type="password" name="password2" value="${form.password2}"/><span>${form.erros.password}</span>
				<br /> 邮 箱： <input type="text" name="email" value="${form.email}"/>${form.erros.email}
				<br /> 昵 称： <input type="text" name="nickname" value="${form.nickname}"/>
				${form.erros.nickname}<br /> 生 日： <input type="text"
				name="birthday" onClick="laydate()"/><span>${form.erros.birthday}</span>
				<br /> 验证码： <input type="text" name="checkimg" /> <img alt="验证码"
				onclick="change(this)" src="${pageContext.request.contextPath}/checkimage"><span>${form.erros.checkimg}</span><br />
				<input type="hidden" value="${token}" name="token">
				<input type="reset" value="重置" /> <input type="submit"  value="提交" />
	</form>
</body>
</html>