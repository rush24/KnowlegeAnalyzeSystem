<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content=""> -->

<title>试卷管理分析系统(教师端)</title>

<!-- Bootstrap Core CSS -->
<link
	href="<c:url value='/index/bower_components/bootstrap/dist/css/bootstrap.min.css'/>"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.css'/>"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="<c:url value='/index/dist/css/timeline.css'/>"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value='/index/dist/css/sb-admin-2.css'/>"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link
	href="<c:url value='/index/bower_components/morrisjs/morris.css'/>"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<c:url value='/index/bower_components/font-awesome/css/font-awesome.min.css'/>"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script
	src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>" />
<script src="<c:url value='/index/js/jquery-ui.min.js'/>"></script>
<script src="<c:url value='/index/js/select-widget-min.js'/>"></script>
<style>
</style>
</head>

<body>
	<div class="sidebar left" role="navigation">
		<div class="sidebar-nav">
			<ul class="nav" id="side-menu">
				<!-- 个人信息 -->
				<li class="sidebar-search">
					<div class="input-group custom-search-form">
						<p class="text-left">老师信息</p>
						<p class="text-left">
							姓名：<span>${sessionScope.name }</span>
						</p>
						<p class="text-left">
							学科：<span>${sessionScope.course }</span>
						</p>
					</div> <!-- /功能 -->
				</li>
				<li><a href="#"><i class="fa fa-dashboard fa-fw"></i> 成绩分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a href="<c:url value='/TeacherGrowServlet?method=loadGrowStu'/>" target="main">学生成长轨迹图</a>
						</li>
						<li>
							<%-- <a href="<c:url value='/TeacherServlet?method=courseInfo&courseName=chinese'/>" target="main">语文</a> --%>
							<a href="<c:url value='/TeacherGrowServlet?method=loadGradeStu'/>" target="main">学生学习履历图</a>
						</li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
						试卷分析<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=语文'/>"
							target="main">语文</a></li>
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=数学'/>"
							target="main">数学</a></li>
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=英文'/>"
							target="main">英语</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>知识点分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="<c:url value='/RelationServlet?method=relation&course=语文'/>"
							target="main">语文</a></li>
						<li><a href="<c:url value='/RelationServlet?method=relation&course=数学'/>"
							target="main">数学</a></li>
						<li><a href="<c:url value='/RelationServlet?method=relation&course=英文'/>"
							target="main">英语</a></li>
					</ul></li>
				 <li>
					<a href="#"><i class="fa fa-files-o fa-fw"></i> 聚类分析<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li>
								<a href="<c:url value='/ClusterServlet?method=load&subject=语文'/>" target="main">语文</a>
                            </li>
							<li>
                                <a href="<c:url value='/ClusterServlet?method=load&subject=数学'/>" target="main">数学</a>
                            </li>
                            <li>
                                <a href="<c:url value='/ClusterServlet?method=load&subject=英文'/>" target="main">英文</a>
                            </li>
                        </ul>
                </li>
			</ul>
		</div>
	</div>


	<!-- /#wrapper -->

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<c:url value='/index/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.js'/>"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="<c:url value='/index/bower_components/raphael/raphael-min.js'/>"></script>
	<script
		src="<c:url value='/index/bower_components/morrisjs/morris.min.js'/>"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<c:url value='/index/dist/js/sb-admin-2.js'/>"></script>

</body>

</html>
