<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
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
    
	<script src="<c:url value='/index/bower_components/jquery/dist/jquery.min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/index/css/drop-down.css'/>"/>
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
							<a href="<c:url value='/TeacherGrowServlet?method=loadGrowStu'/>">学生成长轨迹图</a>
						</li>
						<li>
							<%-- <a href="<c:url value='/TeacherServlet?method=courseInfo&courseName=chinese'/>" target="main">语文</a> --%>
							<a href="<c:url value='/TeacherGrowServlet?method=loadGradeStu'/>">学生学习履历图</a>
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
			<div class="text-center" style="margin-bottom:50px;"><span style="font-size:25px;">学生个人成长轨迹</span>
				<div class="pull-right text-left" style="margin-top:30px;">
					<!-- 下拉选择框 -->
					<form action="" method="get" class="form">
						<select name="drop1" class="ui-select" id="stu-select">
							<option value="选择学生">选择学生</option>
							<c:forEach items="${stuList}" var="stu">
							<option value="${stu}"
								 <c:if test="${stu eq selectStu}" >
									selected='selected'
								</c:if> 
							>
								${stu}
							</option>
						</c:forEach>
						</select>
					</form>
				</div>
			</div>
		    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->	
			<div id="mainchinese" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="mainmath" style="height:400px;width:100%;margin:0 auto;"></div>
			<div id="mainenglish" style="height:400px;width:100%;margin:0 auto;"></div>
		</div>
	</div>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script src="<c:url value='/index/js/grow-t.js'/>"></script>
    <script type="text/javascript">
  //下拉框事件 使用ajax提交到后台
  $(document).ready(function(){		
		$(".ui-select").selectWidget({
			change       : function (changes) {
				 var stu= $("#stu-select option:selected").val(); //学生的姓名
				   if("选择学生"!=stu){  //如果不为空
					   var ajax = new XMLHttpRequest(); //得到ajax对象
					   
					 //设置地址和 方法为post
						var method = "post";
						var url ="<c:url value='/TeacherGrowServlet?method=loadStuPr'/>&time="+new Date().getTime();
						//alert(url);
						 ajax.open(method, url);
						 
						//设置请求头
						ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
						//发送请求
					var content="stuName="+stu;
					ajax.send(content); 
					
					//----------------------------等待-------------------、
					ajax.onreadystatechange = function(){
						if(ajax.readyState==4 && ajax.status==200){
							value0 = [];  //语文的学生的年级  用来放进echart里面的数据
							value1 = [];  //语文的学生的平均pr值
							
							value2 = [];//数学的学生的年级
							value3 = [];//数学的学生的平均pr值
							
							value4 = [];//英语的学生的年级
							value5 = [];//英语的学生的平均pr值
							
							var jsonJava= ajax.responseText;  //这个json是Java书写的json
							
							//转化为js格式的json
							 var json = eval("("+jsonJava+")");
							
							var chineseList=json[0]['chineseList'];
							for(var i=0;i<chineseList.length;i++){
								
								var classyear = chineseList[i]['classyear']
								var avgPR = chineseList[i]['avgPR']
								
								value0.push(classyear);
								value1.push(avgPR);
								//alert(classyear+" "+avgPR);
							}
							
							var mathList=json[0]['mathList'];
							for(var i=0;i<mathList.length;i++){
								var classyear = mathList[i]['classyear']
								var avgPR = mathList[i]['avgPR']
								
								value2.push(classyear);
								value3.push(avgPR);
								//alert(classyear+" "+avgPR);
							}
								
						
							
							
							var englishList=json[0]['englishList'];
							for(var i=0;i<englishList.length;i++){
								var classyear = englishList[i]['classyear']
								var avgPR = englishList[i]['avgPR']
								value4.push(classyear);
								value5.push(avgPR);
								//alert("英语="+classyear+" "+avgPR);
							} 
						
							pic(); //数据传给echart
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
	  /*  document.getElementById("stu-select").onchange=function(){
	   //得到下拉框的值
	   var stu=this[this.selectedIndex].value; //学生的姓名
	   
	   if("选择学生"!=stu){  //如果不为空
		   var ajax = new XMLHttpRequest(); //得到ajax对象
		   
		 //设置地址和 方法为post
			var method = "post";
			var url ="<c:url value='/TeacherGrowServlet?method=loadStuPr'/>&time="+new Date().getTime();
			//alert(url);
			 ajax.open(method, url);
			 
			//设置请求头
			ajax.setRequestHeader("content-type","application/x-www-form-urlencoded");
			//发送请求
		var content="stuName="+stu;
		ajax.send(content); 
		
		//----------------------------等待-------------------、
		ajax.onreadystatechange = function(){
			if(ajax.readyState==4 && ajax.status==200){
				value0 = [];  //语文的学生的年级  用来放进echart里面的数据
				value1 = [];  //语文的学生的平均pr值
				
				value2 = [];//数学的学生的年级
				value3 = [];//数学的学生的平均pr值
				
				value4 = [];//英语的学生的年级
				value5 = [];//英语的学生的平均pr值
				
				var jsonJava= ajax.responseText;  //这个json是Java书写的json
				
				//转化为js格式的json
				 var json = eval("("+jsonJava+")");
				
				var chineseList=json[0]['chineseList'];
				for(var i=0;i<chineseList.length;i++){
					
					var classyear = chineseList[i]['classyear']
					var avgPR = chineseList[i]['avgPR']
					
					value0.push(classyear);
					value1.push(avgPR);
					//alert(classyear+" "+avgPR);
				}
				
				var mathList=json[0]['mathList'];
				for(var i=0;i<mathList.length;i++){
					var classyear = mathList[i]['classyear']
					var avgPR = mathList[i]['avgPR']
					
					value2.push(classyear);
					value3.push(avgPR);
					//alert(classyear+" "+avgPR);
				}
					
			
				
				
				var englishList=json[0]['englishList'];
				for(var i=0;i<englishList.length;i++){
					var classyear = englishList[i]['classyear']
					var avgPR = englishList[i]['avgPR']
					value4.push(classyear);
					value5.push(avgPR);
					//alert("英语="+classyear+" "+avgPR);
				} 
			
				pic(); //数据传给echart
			}
				
		}
		
		   
	   }
} */
	
	
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