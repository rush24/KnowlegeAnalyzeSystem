<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>知诸网</title>
<!-- Bootstrap Core CSS -->
    <link href="<c:url value='/index/bower_components/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.css'/>" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="<c:url value='/index/dist/css/timeline.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/index/dist/css/sb-admin-2.css'/>" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="<c:url value='/index/bower_components/morrisjs/morris.css'/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value='/index/bower_components/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<!-- jQuery -->
    <script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>" />
	<script src="<c:url value='/index/js/jquery-ui.min.js'/>"></script>
	<script src="<c:url value='/index/js/select-widget-min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/modal.css'/>" />
	<style type="text/css">
		.nav > li:hover .dropdown-menu {display: block;}
	</style>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">知诸网</a>
            </div>
            <!-- nav header -->

            <!-- /登录 -->
            <ul class="nav navbar-top-links navbar-right pull-right">
                <li><a class="navbar-brand" href="index.jsp" style="color:#337ab7;">登录</a></li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">	
		                <!-- /功能 -->
		                <li>
							<a href="#"><i class="fa fa-dashboard fa-fw"></i> 成绩分析<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">个人成长轨迹图</a>
		                        </li>
		                        <li>
		                           	<a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">个人学习履历图</a>
		                        </li>
		                    </ul>
		                </li>
		                <li>
							<a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 试卷分析<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li>
										<a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">语文</a>
		                            </li>
									<li>
		                                <a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">数学</a>
		                            </li>
		                            <li>
		                                <a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">英语</a>
		                            </li>
		                        </ul>
		                </li>                                                
		                <li>
							<a href="#"><i class="fa fa-sitemap fa-fw"></i> 知识点分析<span
								class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">语文</a></li>
									<li><a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">数学</a></li>
									<li><a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">英语</a></li>
								</ul>
		                </li>
		                <li>
							<a href="#"><i class="fa fa-files-o fa-fw"></i> 聚类分析<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li>
										<a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">语文</a>
		                            </li>
									<li>
		                                <a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">数学</a>
		                            </li>
		                            <li>
		                                <a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">英语</a>
		                            </li>
		                        </ul>
		                </li>
		                <li>
							<a href="#"><i class="fa fa-files-o fa-fw"></i> 知识地图<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li>
										<a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">语文</a>
		                            </li>
									<li>
		                                <a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">数学</a>
		                            </li>
		                            <li>
		                                <a href="#" data-toggle="modal" data-target="#mylogin" data-backdrop="false" class="selectGrade">英语</a>
		                            </li>
		                        </ul> 
		                </li>
		                <li>
							<a href="#"><i class="glyphicon glyphicon-download"></i> 导出履历<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
								
								<c:forEach items="${sessionScope.gradeList}" var="grade">
							           <li>    
										<a href="#" data-toggle="modal" data-target="#myModal" data-backdrop="false"  class="selectGrade">${grade.name}</a>
		                               </li>
  					            </c:forEach>		
		                        </ul> 
		                </li>
		            </ul>
		            <!-- 提示登录框 -->
		            <div class="modal fade" id="mylogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body text-center">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hide();">
										&times;
									</button>
									<p style="font-size:25px;font-family:'微软雅黑';margin:50px 0;">您还没有登录，请先登录!</p>
									<button type="button" class="btn btn-primary">
										<a class="" href="index.jsp" style="color:white;font-size:20px;text-decoration:none;">登录</a>
									</button>
								</div>
							</div><!-- /.modal-content -->
						</div><!-- /.modal -->
					</div>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
			<div class="panel">
				<div class="panel-body">
					<ul class="timeline">
						<li>
							<div class="timeline-badge">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h3 class="timeline-title">Performance analysis(成绩分析)</h3>
									<p><small class="text-muted"><i class="fa fa-clock-o"></i></small>
									</p>
								</div>
								<div class="timeline-body">
									<img src="<c:url value='/index/image/f1.png'/>" width="30%" style="float:right;margin-top:-10px;">
									<p>In this module we can let the students visually see their results to subjects the results and their rankings. The teacher here can understand the distribution of each fraction of the students, the overall performance of the students, but also to see the results of a student.</p>
								</div>
							</div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-badge warning">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h3 class="timeline-title">Examination paper analysis(试卷分析)</h3>
									<p><small class="text-muted"><i class="fa fa-clock-o"></i></small>
									</p>
								</div>
								<div class="timeline-body">
									<img src="<c:url value='/index/image/f2.png'/>" width="30%" style="float:left;margin-top:-10px;">
									<p>Here we will conduct in-depth analysis of each test papers, you can see every problem of the error rate of the papers, we will put the high error rate of the subject displayed, so that the teacher can be directly under the title of high error rate evaluation papers, many students know the wrong topic, and can know what to or wrong.</p>
								</div>
							</div>
						</li>
						<li>
							<div class="timeline-badge danger">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h3 class="timeline-title">Knowledge point analysis(知识点分析)</h3>
									<p><small class="text-muted"><i class="fa fa-clock-o"></i></small>
									</p>
								</div>
								<div class="timeline-body">
									<img src="<c:url value='/index/image/f3.png'/>" width="30%" style="float:left">
									<p>Knowledge point analysis is one of the modules to show the connection between various knowledge points. The use of the analysis of the correlation between different knowledge points, and its connection, can be drawn from a knowledge point will not and it has the knowledge of the knowledge may not, learning will be organized and targeted.</p>
								</div>
							</div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-badge success">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h3 class="timeline-title">Clustering analysis(聚类分析)</h3>
									<p><small class="text-muted"><i class="fa fa-clock-o"></i></small>
									</p>
								</div>
								<div class="timeline-body">
									<p>The system's knowledge map function is to use the principle of Google map will show the knowledge point on the map. Different grades have related knowledge points will be connected to them. This function is not only based on the textbook knowledge to build a map, you can upload files, according to the contents of the document to build a map.</p>
								</div>
							</div>
						</li>
						<li>
							<div class="timeline-badge info">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h3 class="timeline-title">knowledge map(知识地图)</h3>
									<p><small class="text-muted"><i class="fa fa-clock-o"></i></small>
									</p>
								</div>
								<div class="timeline-body">
									<p>Knowledge point analysis is one of the modules to show the connection between various knowledge points. The use of the analysis of the correlation between different knowledge points, and its connection, can be drawn from a knowledge point will not and it has the knowledge of the knowledge may not, learning will be organized and targeted.</p>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
	<!-- /.panel -->
	</div>
	
	<!-- 模态框js -->
	<script src="<c:url value='/index/js/modal.js'/>"></script>
 <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/index/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.js'/>"></script>

    <!-- Morris Charts JavaScript -->
    <script src="<c:url value='/index/bower_components/raphael/raphael-min.js'/>"></script>
    <script src="<c:url value='/index/bower_components/morrisjs/morris.min.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/index/dist/js/sb-admin-2.js'/>"></script>

</body>
</html>