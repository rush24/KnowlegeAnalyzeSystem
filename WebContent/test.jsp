<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<title>测试页面</title>
</head>
<body>
	<h1 style="color:green;" align="center">${name }</h1>
	<h1 style="color:green;" align="center">${course }</h1>
	<h1 style="color:green;" align="center">${sex }</h1>
	
	<form name="myform" action="<c:url value='/KnowlegeMapServlet?method=fileUpload'/>" method="post"
       enctype="multipart/form-data">
       File:<br>
       <input type="file" name="myfile"><br>
       <br>
       <input type="submit" name="submit" value="Commit" onclick="">
    </form>
</body>
</html>