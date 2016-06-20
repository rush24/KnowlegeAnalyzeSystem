<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>知诸网</title>
<!-- Bootstrap Core CSS -->
<link href="<c:url value='/index/bower_components/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">
<!-- MetisMenu CSS -->
<link	href="<c:url value='/index/bower_components/metisMenu/dist/metisMenu.min.css'/>" rel="stylesheet">
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



<script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>" />
<link rel="stylesheet" href="<c:url value='/index/css/grade-t.css'/>" />
<script src="<c:url value='/index/js/jquery-ui.min.js'/>"></script>
<script src="<c:url value='/index/js/select-widget-min.js'/>"></script>
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

            <!-- /.退出 -->
            <ul class="nav navbar-top-links navbar-right pull-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        ${sessionScope.name }&nbsp;&nbsp;<i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> ${sessionScope.name }</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="<%=request.getContextPath() %>/LogoutServlet" target="_top"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">	
		                <!-- /功能 -->
		                <li><a href="#"><i class="fa fa-dashboard fa-fw"></i> 成绩分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a href="<c:url value='/TeacherGrowServlet?method=loadGrowStu'/>" >学生成长轨迹图</a>
						</li>
						<li>
							<%-- <a href="<c:url value='/TeacherServlet?method=courseInfo&courseName=chinese'/>" target="main">语文</a> --%>
							<a href="<c:url value='/TeacherGrowServlet?method=loadGradeStu'/>" >学生学习履历图</a>
						</li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
						试卷分析<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=语文'/>"
							>语文</a></li>
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=数学'/>"
							>数学</a></li>
						<li><a
							href="<c:url value='/TeacherPaperServlet?method=loadGradeList&subject=英文'/>"
							>英语</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>知识点分析<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="<c:url value='/RelationServlet?method=relation&course=语文'/>"
							>语文</a></li>
						<li><a href="<c:url value='/RelationServlet?method=relation&course=数学'/>"
							>数学</a></li>
						<li><a href="<c:url value='/RelationServlet?method=relation&course=英文'/>"
							>英语</a></li>
					</ul></li>
				 <li>
					<a href="#"><i class="fa fa-files-o fa-fw"></i> 聚类分析<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li>
								<a href="<c:url value='/ClusterServlet?method=load&subject=语文'/>" >语文</a>
                            </li>
							<li>
                                <a href="<c:url value='/ClusterServlet?method=load&subject=数学'/>" >数学</a>
                            </li>
                            <li>
                                <a href="<c:url value='/ClusterServlet?method=load&subject=英文'/>" >英文</a>
                            </li>
                        </ul>
                </li>
                <li>
					<a href="#"><i class="fa fa-files-o fa-fw"></i> 知识地图<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li>
								<a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=语文'/>">语文</a>
                            </li>
							<li>
                                <a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=数学'/>">数学</a>
                            </li>
                            <li>
                                <a href="<c:url value='/KnowlegeMapServlet?method=getMapData&course=英文'/>">英文</a>
                            </li>
                        </ul> 
                </li>
		            </ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper">
			<div class="text-center" style="margin-bottom: 110px;">
				<span style="font-size: 25px;">学生个人学习履历</span>
				<div class="row">
					<div class="col-xs-6 text-left sec">
						<!-- 学生下拉选择框 -->
						<form action="" method="get" class="form">
							<select name="drop1" class="ui-select" id="stu-select">
								<option value="选择学生">选择学生</option>
								<c:forEach items="${stuList}" var="stu">
									<option value="${stu}" <c:if test="${stu eq selectedStu}" > selected='selected'</c:if> >${stu}</option>
								</c:forEach>
							</select>
						</form>
					</div>
					<div class="col-xs-6 text-left sec" id="formdiv2">
						<!-- 年级下拉选择框 -->
						<form action="" method="get" class="form" id="form2">
							<select name="drop1" class="ui-select" id="grade-select">
								<option value="选择年级">选择年级</option>
							</select>
						</form>
					</div>
				</div>
			</div>
			
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->	
			<div id="mainchinese" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="prchinese" style="height:400px;width:100%;margin:0 auto;"></div>	
			<div id="mainmath" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="prmath" style="height:400px;width:100%;margin:0 auto;"></div>	
			<div id="mainenglish" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="prenglish" style="height:400px;width:100%;margin:0 auto;"></div>
		</div>
	</div>
		
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script src="<c:url value='/index/js/grade-t.js'/>"></script>
    
    
    <script type="text/javascript">
	
    //下拉选择框
	$(document).ready(function(){		
		$("#stu-select").selectWidget({
			change       : function (changes) {
				
				var stu = $("#stu-select").find("option:selected").text();
				
				//删除原来下拉选择框
				if(changes != "选择学生"){
				var form = document.getElementById("form2");
				form.parentNode.removeChild(form);}
				
				 if("选择学生"!=stu){
					 
					 var ajax = new XMLHttpRequest(); //得到ajax对象
					   
					 //设置地址和 方法为post
					 var method = "post";
					 var url ="<c:url value='/TeacherGrowServlet?method=loadStuGradeList'/>&time="+new Date().getTime();
					 //alert(url);
					 ajax.open(method, url);
						 
					 //设置请求头
					 ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
					 //发送请求
					 var content="stuName="+stu;
					 ajax.send(content); 
					 ajax.onreadystatechange = function(){
							if(ajax.readyState==4 && ajax.status==200){
								 var jsonJava= ajax.responseText;  //这个json是Java书写的json
								 var json = eval("("+jsonJava+")");
								
								 var div = document.getElementById("formdiv2");
								 var form2 = document.createElement("form");
								 var select = document.createElement("select");
								 
								 for(var i=0;i<json.length;i++){
									var grade=json[i]['classyear'];
									//alert(grade);
									var option = document.createElement("option");
			    					option.innerHTML=grade;
			    					select.appendChild(option);
								 }
								 
								 select.setAttribute("class","ui-select");
								 select.setAttribute("name","drop1");
								 select.setAttribute("id","grade-select");
								 form2.setAttribute("id","form2");
								 form2.setAttribute("class","form");
								 form2.setAttribute("method","get");
								 form2.appendChild(select);
								 div.appendChild(form2);
								 
								 $(document).ready(function(){	
									 $("#grade-select").selectWidget({
											change       : function (changes) {
												var grade= $("#grade-select option:selected").text(); 
												var stu = $("#stu-select option:selected").text();
												if("选择年级"!=grade){
													 var ajax = new XMLHttpRequest(); //得到ajax对象
													   
													 //设置地址和 方法为post
													 var method = "post";
													 var url ="<c:url value='/TeacherGrowServlet?method=loadGradeInfo'/>&time="+new Date().getTime();
													 //alert(url);
													 ajax.open(method, url);
														 
													 //设置请求头
													 ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
													 //发送请求
													 var content="stuName="+stu+"&grade="+grade;
													 ajax.send(content); 
													 
													 ajax.onreadystatechange = function(){
														 if(ajax.readyState==4 && ajax.status==200){
															 var jsonJava= ajax.responseText;  //这个json是Java书写的json
															 var json = eval("("+jsonJava+")");
															 //alert(jsonJava);
															
															//为语文前后侧图赋值
															chineseBefData = [];// 设置语文前测为空
															chineseAftData = [];// 设置语文后测为空
															chineseGradeCutData = [];// 设置语文前后测相差数据为空
															chineseGradeHighData = [];// 设置语文前后测相差数据起点为空
															
														
															
															var chineseGradeBefList=json[0]['chineseGradeBefList'];
															for(var i=0;i<chineseGradeBefList.length;i++){
																if(chineseGradeBefList[i]['score']==0){
																	chineseBefData.push("");
																}else{
																	chineseBefData.push(chineseGradeBefList[i]['score']);
																}
															}
															
															var chineseGradeAftList=json[0]['chineseGradeAftList'];
															for(var i=0;i<chineseGradeAftList.length;i++){
																if(chineseGradeAftList[i]['score']==0){
																	chineseAftData.push("");
																}else{
																	chineseAftData.push(chineseGradeAftList[i]['score']);
																}
															}
															
															var chineseGradeCutList=json[0]['chineseGradeCutList'];
															for(var i=0;i<chineseGradeCutList.length;i++){
																if(chineseGradeCutList[i]['score']==0){
																	chineseGradeCutData.push("");
																}else{
																	chineseGradeCutData.push(chineseGradeCutList[i]['score']);
																}
															}
															
															var chineseGradeHighList=json[0]['chineseGradeHighList'];
															for(var i=0;i<chineseGradeHighList.length;i++){
																if(chineseGradeHighList[i]['score']==0){
																	chineseGradeHighData.push("");
																}else{
																	chineseGradeHighData.push(chineseGradeHighList[i]['score']);
																}
															}
															

													
															
															chineseGradeAndPrData = [];
															var chineseGradeAndPr=json[0]['chineseGradeAndPr'];
															for(var i=0;i<chineseGradeAndPr.length;i++){
																var tempData = [];
																tempData.push(chineseGradeAndPr[i]['score']);
																tempData.push(chineseGradeAndPr[i]['pr']);
																chineseGradeAndPrData.push(tempData);
																
															}
															
															//为数学前后侧图赋值
															mathBefData = [];// 设置数学前测为空
															mathAftData = [];// 设置数学后测为空
															mathGradeCutData = [];// 设置数学前后测相差数据为空
															mathGradeHighData = [];// 设置数学前后测相差数据起点为空
															

															var mathGradeBefList=json[0]['mathGradeBefList'];
															for(var i=0;i<mathGradeBefList.length;i++){
																if(mathGradeBefList[i]['score']==0){
																	mathBefData.push("");
																}else{
																	mathBefData.push(mathGradeBefList[i]['score']);
																}
															}
															
															var mathGradeAftList=json[0]['mathGradeAftList'];
															for(var i=0;i<mathGradeAftList.length;i++){
																if(mathGradeAftList[i]['score']==0){
																	mathAftData.push("");
																}else{
																	mathAftData.push(mathGradeAftList[i]['score']);
																}
															}
															
															var mathGradeCutList=json[0]['mathGradeCutList'];
															for(var i=0;i<mathGradeCutList.length;i++){
																if(mathGradeCutList[i]['score']==0){
																	mathGradeCutData.push("");
																}else{
																	mathGradeCutData.push(mathGradeCutList[i]['score']);
																}
															}
															
															var mathGradeHighList=json[0]['mathGradeHighList'];
															for(var i=0;i<mathGradeHighList.length;i++){
																if(mathGradeHighList[i]['score']==0){
																	mathGradeHighData.push("");
																}else{
																	mathGradeHighData.push(mathGradeHighList[i]['score']);
																}
															}
															
															mathGradeAndPrData = [];
															var mathGradeAndPr=json[0]['mathGradeAndPr'];
															for(var i=0;i<mathGradeAndPr.length;i++){
																var tempData = [];
																tempData.push(mathGradeAndPr[i]['score']);
																tempData.push(mathGradeAndPr[i]['pr']);
																mathGradeAndPrData.push(tempData);
																
															}
															
															//为数学前后侧图赋值
															englishBefData = [];// 设置数学前测为空
															englishAftData = [];// 设置数学后测为空
															englishGradeCutData = [];// 设置数学前后测相差数据为空
															englishGradeHighData = [];// 设置数学前后测相差数据起点为空
															

															var englishGradeBefList=json[0]['englishGradeBefList'];
															for(var i=0;i<englishGradeBefList.length;i++){
																if(englishGradeBefList[i]['score']==0){
																	englishBefData.push("");
																}else{
																	englishBefData.push(englishGradeBefList[i]['score']);
																}
															}
															
															var englishGradeAftList=json[0]['englishGradeAftList'];
															for(var i=0;i<englishGradeAftList.length;i++){
																if(englishGradeAftList[i]['score']==0){
																	englishAftData.push("");
																}else{
																	englishAftData.push(englishGradeAftList[i]['score']);
																}
															}
															
															var englishGradeCutList=json[0]['englishGradeCutList'];
															for(var i=0;i<englishGradeCutList.length;i++){
																if(englishGradeCutList[i]['score']==0){
																	englishGradeCutData.push("");
																}else{
																	englishGradeCutData.push(englishGradeCutList[i]['score']);
																}
															}
															
															var englishGradeHighList=json[0]['englishGradeHighList'];
															for(var i=0;i<englishGradeHighList.length;i++){
																if(englishGradeHighList[i]['score']==0){
																	englishGradeHighData.push("");
																}else{
																	englishGradeHighData.push(englishGradeHighList[i]['score']);
																}
															}
															
															englishGradeAndPrData = [];
															var englishGradeAndPr=json[0]['englishGradeAndPr'];
															for(var i=0;i<englishGradeAndPr.length;i++){
																var tempData = [];
																tempData.push(englishGradeAndPr[i]['score']);
																tempData.push(englishGradeAndPr[i]['pr']);
																englishGradeAndPrData.push(tempData);
																
															}
															
															pic();
														 }
													 }
												 }
											},
											effect       : "slide",
											keyControl   : true,
											speed        : 200,
											scrollHeight : 250
										}); 
								 });
							
							}
				     }
			    }
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		}); 
		
		$("#grade-select").selectWidget({
			change       : function (changes) {
				return changes;
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		});
	});
		
		
	
    </script>

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