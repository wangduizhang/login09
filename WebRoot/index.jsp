<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>教学系统</title>
</head>
<body>
<c:if test="${user!=null}">
欢迎:${user.username}<br/>
<a href="${pageContext.request.contextPath}/loginout">注销</a>
</c:if>
<c:if test="${user==null}">
<a href="${pageContext.request.contextPath}/login">登陆</a>
<a href="${pageContext.request.contextPath}/sigin">注册</a>
</c:if>
</body>
</html>