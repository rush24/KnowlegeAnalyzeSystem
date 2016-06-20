<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>试卷管理系统(教师端)</title>
	</head>
	<frameset rows="40,*" framespacing="2" frameborder="yes" border="1" bordercolor="#e7e7e7">
		<frame src="<c:url value='/index/jsp/top.jsp'/>" noresize="noresize" name="topFrame" scrolling="No" id="topFrame" title="topFrame">
		<frameset cols="250,*">
			<frame src="<c:url value='/index/jsp/left-tea.jsp'/>" noresize="noresize" name="leftFrame" scrolling="No" id="leftFrame" title="leftFrame">
			<frame src="<c:url value='/index/html/firstpage.html'/>" name="main">
		</frameset>
	</frameset>
</html>